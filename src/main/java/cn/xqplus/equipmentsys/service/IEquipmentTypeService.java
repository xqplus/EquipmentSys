package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.model.EquipmentType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 设备类型信息 服务层
 */

public interface IEquipmentTypeService extends IService<EquipmentType> {

    /**
     * @param page 分页辅助
     * @param wrapper 搜索查询条件
     * @return Page<EquipmentTypeForm>
     */
    IPage<EquipmentTypeForm> selectPage(Page<EquipmentTypeForm> page, EquipmentTypeForm wrapper);

    /**
     * 获取最新的设备类型编号下一个，用于新增
     * @return equipTypeNumber
     */
    EquipmentType getNextEquipTypeNumber();

    /**
     * 设备类型变更
     * @param equipmentType 新的设备类型信息
     * @return String
     */
    String updateEquipType(EquipmentType equipmentType);

    /**
     * 设备类型删除
     * @param id 设备类型id
     * @return String
     */
    String deleteEquipTypeById(int id);
}
