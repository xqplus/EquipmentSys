package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.ext.CommonConst;
import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.mapper.IEquipmentTypeMapper;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.EquipmentType;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IEquipmentTypeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class EquipmentTypeServiceImpl extends ServiceImpl<IEquipmentTypeMapper, EquipmentType>
        implements IEquipmentTypeService {

    @Autowired
    private IEquipmentTypeMapper equipmentTypeMapper;

    @Autowired
    private IEquipmentService equipmentService;

    @Override
    public IPage<EquipmentTypeForm> selectPage(Page<EquipmentTypeForm> page, EquipmentTypeForm wrapper) {

        IPage<EquipmentTypeForm> iPage = equipmentTypeMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (EquipmentTypeForm equipmentTypeForm : iPage.getRecords()) {
                // 时间转换
                equipmentTypeForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(equipmentTypeForm.getCreateTime()));
                equipmentTypeForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(equipmentTypeForm.getUpdateTime()));
            }
        }
        page.setTotal(iPage.getRecords().size());

        return iPage;
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
        }
        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setEquipTypeNumber("001");
        return equipmentType;
    }

    @Override
    public String updateEquipType(EquipmentType equipmentType) {
        Equipment equipment = new Equipment();
        equipment.setEquipTypeNumber(equipmentType.getEquipTypeNumber());
        Equipment equipment1 = equipmentService.getOne(new QueryWrapper<>(equipment)
                .last("LIMIT 1"));
        if (null == equipment1) {
            // 根据设备类型编号变更
            int i = equipmentTypeMapper.update(equipmentType, new UpdateWrapper<EquipmentType>()
                    .eq("equip_type_number", equipmentType.getEquipTypeNumber()));
            if (i >= 1) {
                return CommonConst.SUCCESS;
            }
            return CommonConst.ERROR;
        }
        // 类型下存在设备
        return CommonConst.EXISTS_EQUIP;
    }

    @Override
    public String deleteEquipTypeById(int id) {
        // 判断类型下是否有设备
        EquipmentType equipmentType = equipmentTypeMapper.selectById(id);
        Equipment equipment = new Equipment();
        equipment.setEquipTypeNumber(equipmentType.getEquipTypeNumber());
        Equipment one = equipmentService.getOne(new QueryWrapper<>(equipment)
                .last("LIMIT 1"));
        if (null == one) {
            int i = equipmentTypeMapper.deleteById(id);
            if (i >= 1) {
                return CommonConst.SUCCESS;
            }
            return CommonConst.ERROR;
        }
        // 类型下存在设备
        return CommonConst.EXISTS_EQUIP;
    }

}
