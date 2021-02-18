package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.BusinessEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 密码可见信息（非加密密码） 对应表 t_password_visible
 */

@Data
@TableName("t_password_visible")
public class PasswordVisible extends BusinessEntity implements Serializable {

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

}
