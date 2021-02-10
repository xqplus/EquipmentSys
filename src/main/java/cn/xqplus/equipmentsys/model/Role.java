package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色权限信息 对应表 t_role
 */

@Data
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色编号，角色类型
     */
    @TableField(value = "role_type", jdbcType = JdbcType.INTEGER)
    private Integer roleType;

    /**
     * security权限认证角色名
     */
    @TableField(value = "role_auth", jdbcType = JdbcType.VARCHAR)
    private String roleAuth;

    /**
     * 角色名称
     */
    @TableField(value = "role_name", jdbcType = JdbcType.VARCHAR)
    private String roleName;

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
