package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.form.RepairForm;
import cn.xqplus.equipmentsys.mapper.IRepairMapper;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.response.RepairHistoryResp;
import cn.xqplus.equipmentsys.response.UserResp;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import cn.xqplus.equipmentsys.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * 维修信息 服务层实现
 */

@Service
public class RepairServiceImpl extends ServiceImpl<IRepairMapper, Repair>
        implements IRepairService {

    @Autowired
    private IRepairMapper repairMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IEquipmentService equipmentService;

    @Override
    public IPage<RepairForm> selectPage(Page<RepairForm> page, RepairForm wrapper) {

        IPage<RepairForm> iPage = repairMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (RepairForm repairForm : iPage.getRecords()) {
                // 时间转换
                repairForm.setReportDate(new SimpleDateFormat("yyyy-MM-dd").format(repairForm.getReportTime()));
                // 设备状态转换（String展示）
                if (repairForm.getEquipState() == 1) {
                    repairForm.setCurrentState("待维修");
                }
                // 设置报修人
                repairForm.setReporterName(repairForm.getUserName());
            }
        }
        page.setTotal(iPage.getRecords().size());

        return iPage;
    }

    @Override
    public IPage<RepairForm> selectHistoryPage(Page<RepairForm> page, RepairForm wrapper) {

        IPage<RepairForm> iPage = repairMapper.getHistoryList(page, wrapper);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (RepairForm repairForm : iPage.getRecords()) {
                // 设置维修人
                repairForm.setRepairerName(repairForm.getUserName());
                // 设置报修人 TODO 循环里sql 数据量大时有效率问题
                User user = userService.getOne(new QueryWrapper<User>()
                        .eq("user_number", repairForm.getReporterNumber()));
                repairForm.setReporterName(user.getUserName());
                // 时间转换
                repairForm.setReportDate(new SimpleDateFormat("yyyy-MM-dd").format(repairForm.getReportTime()));
                repairForm.setRepairDate(new SimpleDateFormat("yyyy-MM-dd").format(repairForm.getRepairTime()));
                // 状态转换
                if (repairForm.getRepairState() == 0) {
                    repairForm.setRepairStateName("维修完成");
                }
                if (repairForm.getRepairState() == 2) {
                    repairForm.setRepairStateName("报废处理");
                }
            }
        }
        iPage.setTotal(iPage.getRecords().size());

        return iPage;
    }

    @Override
    public Repair getNextRepairNumber() {
        List<Repair> listByNumberDesc = repairMapper.getListByNumberDesc();
        Repair repair = new Repair();
        if (CollectionUtils.isNotEmpty(listByNumberDesc)) {
            String number = listByNumberDesc.get(0).getRepairNumber();
            String nextNumber = String.valueOf(Integer.parseInt(number) + 1);
            // 这里的算法限定维修编号五位数 <100000
            String[] zeros = {"", "0000", "000", "00", "0", ""};
            repair.setRepairNumber(zeros[nextNumber.length()] + nextNumber);
        } else {
            repair.setRepairNumber("00001");
        }
        return repair;
    }

    @Override
    public boolean repair(int id) {
        Repair repair = repairMapper.selectById(id);
        Equipment equipment = new Equipment();
        equipment.setEquipState(0);
        // 更新设备状态
        boolean equipUpdate = equipmentService.update(equipment, new UpdateWrapper<Equipment>()
                .eq("equip_number", repair.getEquipNumber()));
        Repair repair1 = new Repair();
        repair1.setRepairerNumber(userService.getCurrentUserInfo().getUserNumber());
        // 维修完成，维修状态
        repair1.setRepairState(0);
        // 更新维修信息
        int repairUpdate = repairMapper.update(repair1, new UpdateWrapper<Repair>()
                .eq("id", id));

        return (equipUpdate && (repairUpdate >= 1));
    }

    @Override
    public String scrap(int id) {
        Repair repair = repairMapper.selectById(id);
        Equipment equipment = equipmentService.getOne(new QueryWrapper<Equipment>()
                .eq("equip_number", repair.getEquipNumber()));
        // 只有维修中的设备才能报废
        if (equipment.getEquipState() == 1) {
            Equipment equipment1 = new Equipment();
            equipment1.setEquipState(2);
            boolean update = equipmentService.update(equipment1, new UpdateWrapper<Equipment>()
                    .eq("equip_number", equipment.getEquipNumber()));
            Repair repair1 = new Repair();
            repair1.setRepairerNumber(userService.getCurrentUserInfo().getUserNumber());
            // 报废处理
            repair1.setRepairState(2);
            int repairUpdate = repairMapper.update(repair1, new UpdateWrapper<Repair>()
                    .eq("id", id));
            if (update && (repairUpdate >= 1)) {
                return "success";
            } else {
                return "error";
            }
        } else {
            // 流程不正确 （使用中 -报修-> 维修中 -报废-> 已报废）
            return "noProcess";
        }
    }

    @Override
    public void exportExcel(List<String> ids, HttpServletResponse response) {
        List<RepairForm> repairFormList = repairMapper
                .getHistoryList(new Page<>(), new RepairForm())
                .getRecords();
        List<RepairHistoryResp> exportList = new ArrayList<>();

        for (RepairForm rf : repairFormList) {
            // 设置报修人 TODO 循环里sql 数据量大时有效率问题
            User user = userService.getOne(new QueryWrapper<User>()
                    .eq("user_number", rf.getReporterNumber()));
            rf.setReporterName(user.getUserName());
            // 设置维修人
            rf.setRepairerName(rf.getUserName());
            // 状态转换
            if (rf.getRepairState() == 0) {
                rf.setRepairStateName("维修完成");
            }
            if (rf.getRepairState() == 2) {
                rf.setRepairStateName("报废处理");
            }
            // 时间转换
            rf.setReportDate(new SimpleDateFormat("yyyy-MM-dd").format(rf.getReportTime()));
            rf.setRepairDate(new SimpleDateFormat("yyyy-MM-dd").format(rf.getRepairTime()));

            RepairHistoryResp repairHistoryResp = new RepairHistoryResp();
            try {
                BeanUtils.copyProperties(repairHistoryResp, rf);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            exportList.add(repairHistoryResp);
        }
        ExcelUtils.exportExcel(exportList, "设备管理系统维修历史信息", "维修历史信息",
                RepairHistoryResp.class, "设备管理系统维修历史信息-" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date().getTime()), response);
    }
}
