package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.mapper.IEquipmentTypeMapper;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.EquipmentType;
import cn.xqplus.equipmentsys.service.IEquipmentTypeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
 * 设备类型信息 服务层实现
 */

@Service
public class EquipmentTypeServiceImpl implements IEquipmentTypeService {

    @Autowired
    private IEquipmentTypeMapper equipmentTypeMapper;

    @Override
    public Page<EquipmentTypeForm> selectPage(Page<EquipmentTypeForm> page, EquipmentTypeForm wrapper) {
        List<EquipmentTypeForm> equipmentTypeForms = equipmentTypeMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(equipmentTypeForms)) {
            for (EquipmentTypeForm equipmentTypeForm : equipmentTypeForms) {
                // 时间转换
                equipmentTypeForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(equipmentTypeForm.getCreateTime()));
                equipmentTypeForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(equipmentTypeForm.getUpdateTime()));
            }
        }
        // 设置返回状态码
        page.setMaxLimit(0L);
        // 设置msg
        page.setCountId("success");
        page.setRecords(equipmentTypeForms);
        page.setTotal(equipmentTypeForms.size());
        return page;
    }

    @Override
    public EquipmentType getNextEquipTypeNumber() {
        List<EquipmentType> listByNumberDesc = equipmentTypeMapper.getListByNumberDesc();
        if (CollectionUtils.isNotEmpty(listByNumberDesc)) {
            // 设置最新设备类型编号返回值
            String next = String.valueOf(Integer.parseInt(listByNumberDesc.get(0).getEquipTypeNumber()) + 1);
            // 这里限定设备类型编号3位（<1000）
            String[] zeros = {"", "00", "0", ""};
            listByNumberDesc.get(0).setEquipTypeNumber(zeros[next.length()] + next);
            return listByNumberDesc.get(0);
        } else {
            EquipmentType equipmentType = new EquipmentType();
            equipmentType.setEquipTypeNumber("001");
            return equipmentType;
        }
    }

    @Override
    public boolean save(EquipmentType entity) {
        int insert = equipmentTypeMapper.insert(entity);
        return (insert >= 1);
    }

    @Override
    public boolean update(EquipmentType entity, Wrapper<EquipmentType> updateWrapper) {
        int update = equipmentTypeMapper.update(entity, updateWrapper);
        return (update >= 1);
    }

    @Override
    public boolean removeById(Serializable id) {
        int i = equipmentTypeMapper.deleteById(id);
        return (i >= 1);
    }

    @Override
    public EquipmentType getById(Serializable id) {
        return equipmentTypeMapper.selectById(id);
    }

    @Override
    public boolean saveBatch(Collection<EquipmentType> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<EquipmentType> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<EquipmentType> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(EquipmentType entity) {
        return false;
    }

    @Override
    public EquipmentType getOne(Wrapper<EquipmentType> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<EquipmentType> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<EquipmentType> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<EquipmentType> getBaseMapper() {
        return null;
    }

    @Override
    public Class<EquipmentType> getEntityClass() {
        return null;
    }
}
