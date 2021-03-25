package cn.xqplus.equipmentsys.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 设备信息 Excel 导入导出实体类
 */
@Getter
@Setter
public class EquipmentResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
    @Excel(name = "设备编号", orderNum = "0", width = 15)
    private String equipNumber;

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
     * 设备概述
     */
    @Excel(name = "设备概述", orderNum = "3", width = 30)
    private String equipSummary;

    /**
     * 设备状态
     */
    @Excel(name = "设备状态", orderNum = "4", width = 15)
    private String equipStateName;

    /**
     * 创建时间（yyyy-MM-dd）
     */
    @Excel(name = "创建时间", orderNum = "5", width = 20)
    private String createDate;

    /**
     * 修改时间（yyyy-MM-dd）
     */
    @Excel(name = "修改时间", orderNum = "6", width = 20)
    private String updateDate;

}
