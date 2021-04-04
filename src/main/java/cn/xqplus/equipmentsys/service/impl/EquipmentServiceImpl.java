package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.mapper.IEquipmentMapper;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.response.EquipmentResp;
import cn.xqplus.equipmentsys.response.UserResp;
import cn.xqplus.equipmentsys.service.ICommonService;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import cn.xqplus.equipmentsys.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
 * 设备信息 服务层实现
 */

@Service
public class EquipmentServiceImpl extends ServiceImpl<IEquipmentMapper, Equipment>
        implements IEquipmentService {

    @Autowired
    private IEquipmentMapper equipmentMapper;

    @Autowired
    private IRepairService repairService;

    @Autowired
    private IUserService userService;

    @Override
    public IPage<EquipmentForm> selectPage(Page<EquipmentForm> page, EquipmentForm wrapper) {

        IPage<EquipmentForm> iPage = equipmentMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (EquipmentForm equipmentForm : iPage.getRecords()) {
                // 时间转换
                equipmentForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(equipmentForm.getCreateTime()));
                equipmentForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(equipmentForm.getUpdateTime()));
                // 设备状态转换（String展示）
                if (equipmentForm.getEquipState() == 0) {
                    equipmentForm.setEquipStateName("使用中");
                }
                if (equipmentForm.getEquipState() == 1) {
                    equipmentForm.setEquipStateName("维修中");
                }
                if (equipmentForm.getEquipState() == 2) {
                    equipmentForm.setEquipStateName("已报废");
                }
            }
        }
        page.setTotal(iPage.getRecords().size());

        return iPage;
    }

    @Override
    public Equipment getNextEquipNumber() {
        List<Equipment> listByNumberDesc = equipmentMapper.getListByNumberDesc();
        if (CollectionUtils.isNotEmpty(listByNumberDesc)) {
            // 设置最新设备编号返回值
            String next = String.valueOf(Integer.parseInt(listByNumberDesc.get(0).getEquipNumber()) + 1);
            // 这里限定设备编号4位（<10000）
            String[] zeros = {"", "000", "00", "0", ""};
            listByNumberDesc.get(0).setEquipNumber(zeros[next.length()] + next);
            return listByNumberDesc.get(0);
        } else {
            Equipment equipment = new Equipment();
            equipment.setEquipNumber("0001");
            return equipment;
        }
    }

    @Override
    public String reportRepair(int id, String faultRemark) {
        Equipment equipment = equipmentMapper.selectById(id);

        // 只有使用中的设备才能报修
        if (equipment.getEquipState() != 0) {
            // 流程不正确 （使用中 -报修-> 维修中 -报废-> 已报废）
            return "noProcess";
        }

        // 改变设备状态
        Equipment equipment1 = new Equipment();
        equipment1.setEquipState(1);
        equipment1.setId(id);
        int id1 = equipmentMapper.updateById(equipment1);

        // 创建维修信息
        Repair repair = repairService.getNextRepairNumber();
        repair.setEquipNumber(equipment.getEquipNumber());
        repair.setFaultRemark(faultRemark);

        // 获得当前登录用户
        User currentUserInfo = userService.getCurrentUserInfo();
        repair.setReporterNumber(currentUserInfo.getUserNumber());
        boolean save = repairService.save(repair);
        if ((id1 >= 1) && save) {
            return "success";
        }
        return "error";
    }

    @Override
    public void exportExcel(List<String> ids, HttpServletResponse response) {
        List<EquipmentForm> equipmentFormList = equipmentMapper
                .getList(new Page<>(), new EquipmentForm())
                .getRecords();
        List<EquipmentResp> exportList = new ArrayList<>();

        for (EquipmentForm ef : equipmentFormList) {
            // 时间转换
            ef.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(ef.getCreateTime()));
            ef.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(ef.getUpdateTime()));
            // 设备状态转换
            if (ef.getEquipState() == 0) {
                ef.setEquipStateName("使用中");
            }
            if (ef.getEquipState() == 1) {
                ef.setEquipStateName("维修中");
            }
            if (ef.getEquipState() == 2) {
                ef.setEquipStateName("已报废");
            }

            EquipmentResp equipmentResp = new EquipmentResp();
            try {
                BeanUtils.copyProperties(equipmentResp, ef);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            exportList.add(equipmentResp);
        }
        ExcelUtils.exportExcel(exportList, "设备管理系统设备信息", "设备信息",
                EquipmentResp.class, "设备管理系统设备信息-" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date().getTime()), response);
    }

}
