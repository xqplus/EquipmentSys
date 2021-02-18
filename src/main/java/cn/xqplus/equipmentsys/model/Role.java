package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.BusinessEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 角色权限信息 对应表 t_role
 */

@Data
@TableName("t_role")
public class Role extends BusinessEntity implements Serializable {

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

}
