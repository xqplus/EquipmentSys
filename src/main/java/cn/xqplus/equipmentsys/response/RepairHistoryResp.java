package cn.xqplus.equipmentsys.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 维修历史信息 Excel 导入导出实体类
 */
@Getter
@Setter
public class RepairHistoryResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 维修编号
     */
    @Excel(name = "维修编号", orderNum = "0", width = 15)
    private String repairNumber;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称", orderNum = "1", width = 15)
    private String equipName;

    /**
     * 设备类型名称
     */
    @Excel(name = "设备类型名称", orderNum = "2", width = 20)
    private String equipTypeName;

    /**
     * 报修人
     */
    @Excel(name = "报修人", orderNum = "3", width = 15)
    private String reporterName;

    /**
     * 故障描述
     */
    @Excel(name = "故障描述", orderNum = "4", width = 30)
    private String faultRemark;

    /**
     * 报修时间
     */
    @Excel(name = "报修时间", orderNum = "5", width = 20)
    private String reportDate;

    /**
     * 维修人
     */
    @Excel(name = "维修人", orderNum = "6", width = 15)
    private String repairerName;

    /**
     * 维修日志
     */
    @Excel(name = "维修日志", orderNum = "7", width = 30)
    private String repairLog;

    /**
     * 维修时间
     */
    @Excel(name = "维修时间", orderNum = "8", width = 20)
    private String repairDate;

    /**
     * 维修情况
     */
    @Excel(name = "维修情况", orderNum = "9", width = 15)
    private String repairStateName;

}
