package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门信息 对应表 t_department
 */

@Data
@TableName("t_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门编号
     */
    @TableField(value = "dept_number", jdbcType = JdbcType.VARCHAR)
    private String deptNumber;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name", jdbcType = JdbcType.VARCHAR)
    private String deptName;

    /**
     * 部门简介
     */
    @TableField(value = "dept_introduce", jdbcType = JdbcType.VARCHAR)
    private String deptIntroduce;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 逻辑删除（0 存在 1 删除）
     */
    @TableField(value = "is_del", jdbcType = JdbcType.INTEGER)
    @TableLogic
    @NotNull
    private Integer isDel = 0;

}
