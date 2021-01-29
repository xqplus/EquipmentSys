package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.EquipmentType;
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
    Page<EquipmentTypeForm> selectPage(Page<EquipmentTypeForm> page, EquipmentTypeForm wrapper);

    /**
     * 获取最新的设备类型编号下一个，用于新增
     * @return equipTypeNumber
     */
    EquipmentType getNextEquipTypeNumber();
}
