package cn.xqplus.equipmentsys.form;

import cn.xqplus.equipmentsys.model.Apply;
import lombok.Data;

/**
 * 申请信息业务表单
 */

@Data
public class ApplyForm extends Apply {

    private static final long serialVersionUID = 1L;

    /**
     * 申请类型名称
     */
    private String applyTypeName;

    /**
     * 申请人
     */
    private String userName;

    /**
     * 当前职位
     */
    private String roleName;

    /**
     * 申请意向部门名称
     */
    private String deptName;

    /**
     * 申请状态名称
     */
    private String applyStateName;

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
