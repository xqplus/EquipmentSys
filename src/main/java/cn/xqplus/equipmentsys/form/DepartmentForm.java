package cn.xqplus.equipmentsys.form;

import cn.xqplus.equipmentsys.model.Department;
import lombok.Data;

/**
 * 部门信息业务表单
 */

@Data
public class DepartmentForm extends Department {

    private static final long serialVersionUID = 1L;

    /**
     * 角色类型（部门类型）
     */
    private Integer roleType;

    /**
     * 创建时间（查询显示 yyyy-MM-dd 格式）
     */
    private String createDate;

    /**
     * 更新时间（查询显示 yyyy-MM-dd 格式）
     */
    private String updateDate;

    /**
     * 部门类型（查询使用 无实际意义）
     */
    private String deptType;

    /**
     * 开始时间（查询传参 时间戳）
     */
    private Long startTime;

    /**
     * 结束时间（查询传参 时间戳）
     */
    private Long endTime;

}
