package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 密码可见信息（非加密密码） 对应表 t_password_visible
 */

@Data
@TableName("t_password_visible")
public class PasswordVisible implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "user_name", jdbcType = JdbcType.VARCHAR)
    private String userName;

    /**
     * 可见密码
     */
    @TableField(value = "password_visible", jdbcType = JdbcType.VARCHAR)
    private String passwordVisible;

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
