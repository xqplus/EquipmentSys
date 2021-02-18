package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.BusinessEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 部门信息 对应表 t_department
 */

@Data
@TableName("t_department")
public class Department extends BusinessEntity implements Serializable {

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

}
