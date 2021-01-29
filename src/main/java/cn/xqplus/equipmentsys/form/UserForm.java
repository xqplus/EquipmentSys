package cn.xqplus.equipmentsys.form;

import cn.xqplus.equipmentsys.model.User;
import lombok.Data;

/**
 * 用户信息业务表单
 */

@Data
public class UserForm extends User {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色名称
     */
    private String roleName;

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

//    /**
//     * layui code
//     */
//    private long code = 0;
//
//    /**
//     * layui msg
//     */
//    private String msg = "";
}
