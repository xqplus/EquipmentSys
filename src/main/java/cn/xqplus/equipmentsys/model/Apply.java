package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.LogicDeleteEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 申请信息 对应表 t_position_apply
 */

@Data
@TableName("t_position_apply")
public class Apply extends LogicDeleteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申请编号
     */
    @TableField(value = "apply_number", jdbcType = JdbcType.VARCHAR)
    private String applyNumber;

    /**
     * 申请类型 0 管理员 2 维修员
     */
    @TableField(value = "apply_type", jdbcType = JdbcType.INTEGER)
    private Integer applyType;

    /**
     * 申请人编号
     */
    @TableField(value = "user_number", jdbcType = JdbcType.VARCHAR)
    private String userNumber;

    /**
     * 申请部门编号
     */
    @TableField(value = "dept_number", jdbcType = JdbcType.VARCHAR)
    private String deptNumber;

    /**
     * 申请理由
     */
    @TableField(value = "apply_reason", jdbcType = JdbcType.VARCHAR)
    private String applyReason;

    /**
     * 申请状态 0 审批中 1 审批通过 2 审批驳回
     */
    @TableField(value = "apply_state", jdbcType = JdbcType.INTEGER)
    private Integer applyState;

    /**
     * 审批人
     */
    @TableField(value = "approver_name", jdbcType = JdbcType.VARCHAR)
    private String approverName;

    /**
     * 审批意见
     */
    @TableField(value = "approval_opinion", jdbcType = JdbcType.VARCHAR)
    private String approvalOpinion;

    /**
     * 创建时间（申请时间）
     */
    @TableField(value = "create_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 更新时间（审批时间）
     */
    @TableField(value = "update_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.UPDATE)
    private Long updateTime;

}
