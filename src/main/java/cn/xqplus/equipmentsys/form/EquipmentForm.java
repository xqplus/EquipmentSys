package cn.xqplus.equipmentsys.form;

import cn.xqplus.equipmentsys.model.Equipment;
import lombok.Data;

/**
 * 设备信息业务表单
 */

@Data
public class EquipmentForm extends Equipment {

    private static final long serialVersionUID = 1L;

    /**
     * 设备类型名称（数据展示）
     */
    private String equipTypeName;

    /**
     * 设备状态名称（数据展示 String）
     */
    private String equipStateName;

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
