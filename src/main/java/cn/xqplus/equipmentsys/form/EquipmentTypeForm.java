package cn.xqplus.equipmentsys.form;

import cn.xqplus.equipmentsys.model.EquipmentType;
import lombok.Data;

/**
 * 设备类型信息业务表单
 */

@Data
public class EquipmentTypeForm extends EquipmentType {

    /**
     * 创建时间（查询显示 yyyy-MM-dd 格式）
     */
    private String createDate;

    /**
     * 更新时间（查询显示 yyyy-MM-dd 格式）
     */
    private String updateDate;

    /**
     * 开始时间（查询传参 时间戳）
     */
    private Long startTime;

    /**
     * 结束时间（查询传参 时间戳）
     */
    private Long endTime;
}
