package cn.xqplus.equipmentsys.form;

import cn.xqplus.equipmentsys.model.Repair;
import lombok.Data;

/**
 * 维修信业务表单
 */

@Data
public class RepairForm extends Repair {

    private static final long serialVersionUID = 1L;

    /**
     * 设备名称
     */
    private String equipName;

    /**
     * 设备类型编号
     */
    private String equipTypeNumber;

    /**
     * 设备类型名称
     */
    private String equipTypeName;

    /**
     * 报修人（用户名 userName）
     */
    private String reporterName;

    /**
     * 设备状态
     */
    private Integer equipState;

    /**
     * 当前状态（设备状态 equipState）
     */
    private String currentState;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 维修人
     */
    private String repairerName;

    /**
     * 报修时间（String）
     */
    private String reportDate;

    /**
     * 维修时间（String）
     */
    private String repairDate;

    /**
     * 开始时间（查询传参 时间戳）
     */
    private Long startTime;

    /**
     * 结束时间（查询传参 时间戳）
     */
    private Long endTime;
}
