package cn.xqplus.equipmentsys.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 部门信息 Excel 导入导出实体类
 */
@Getter
@Setter
public class DepartmentResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编号
     */
    @Excel(name = "部门编号", orderNum = "0", width = 15)
    private String deptNumber;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称", orderNum = "1", width = 15)
    private String deptName;

    /**
     * 部门简介
     */
    @Excel(name = "部门简介", orderNum = "2", width = 30)
    private String deptIntroduce;

    /**
     * 创建时间（yyyy-MM-dd）
     */
    @Excel(name = "创建时间", orderNum = "3", width = 20)
    private String createDate;

    /**
     * 修改时间（yyyy-MM-dd）
     */
    @Excel(name = "修改时间", orderNum = "4", width = 20)
    private String updateDate;

}
