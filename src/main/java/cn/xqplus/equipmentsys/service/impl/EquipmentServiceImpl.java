package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.mapper.IEquipmentMapper;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 设备信息 服务层实现
 */

@Service
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private IEquipmentMapper equipmentMapper;

    @Autowired
    private IRepairService repairService;

    @Autowired
    private IUserService userService;

    @Override
    public Page<EquipmentForm> selectPage(Page<EquipmentForm> page, EquipmentForm wrapper) {
        List<EquipmentForm> equipmentForms = equipmentMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(equipmentForms)) {
            for (EquipmentForm equipmentForm : equipmentForms) {
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
        // 设置返回状态码
        page.setMaxLimit(0L);
        // 设置msg
        page.setCountId("success");
        page.setRecords(equipmentForms);
        page.setTotal(equipmentForms.size());
        return page;
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
    public String reportRepair(int id) {
        Equipment equipment = equipmentMapper.selectById(id);
        // 只有使用中的设备才能报修
        if (equipment.getEquipState() == 0) {
            Equipment equipment1 = new Equipment();
            equipment1.setEquipState(1);
            // 改变设备状态
            int id1 = equipmentMapper.update(equipment1, new UpdateWrapper<Equipment>()
                    .eq("id", id));
            // 创建维修信息
            Repair repair = repairService.getNextRepairNumber();
            repair.setEquipNumber(equipment.getEquipNumber());
            // 获得当前登录用户
            User currentUserInfo = userService.getCurrentUserInfo();
            repair.setReporterNumber(currentUserInfo.getUserNumber());
            boolean save = repairService.save(repair);
            if ((id1 >= 1) && save) {
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
    public boolean save(Equipment entity) {
        int insert = equipmentMapper.insert(entity);
        return (insert >= 1);
    }

    @Override
    public boolean update(Equipment entity, Wrapper<Equipment> updateWrapper) {
        int update = equipmentMapper.update(entity, updateWrapper);
        return (update >= 1);
    }

    @Override
    public boolean removeById(Serializable id) {
        int i = equipmentMapper.deleteById(id);
        return (i >= 1);
    }

    @Override
    public Equipment getOne(Wrapper<Equipment> queryWrapper) {
        return equipmentMapper.selectOne(queryWrapper);
    }

    @Override
    public Equipment getById(Serializable id) {
        return equipmentMapper.selectById(id);
    }

    @Override
    public boolean saveBatch(Collection<Equipment> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Equipment> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Equipment> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Equipment entity) {
        return false;
    }

    @Override
    public Equipment getOne(Wrapper<Equipment> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Equipment> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Equipment> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Equipment> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Equipment> getEntityClass() {
        return null;
    }
}
