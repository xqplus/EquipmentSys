package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.istack.internal.NotNull;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 用户信息 对应表 t_user
 */

@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所在部门id
     */
    @TableField(value = "dept_number", jdbcType = JdbcType.VARCHAR)
    private String deptNumber;

    /**
     * 用户编号
     */
    @TableField(value = "user_number", jdbcType = JdbcType.VARCHAR)
    private String userNumber;

    /**
     * 用户名
     */
    @TableField(value = "user_name", jdbcType = JdbcType.VARCHAR)
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "password", jdbcType = JdbcType.VARCHAR)
    private String password;

    /**
     * 真实姓名
     */
    @TableField(value = "true_name", jdbcType = JdbcType.VARCHAR)
    private String trueName;

    /**
     * 角色编号，角色类型（0 管理员 1 普通用户 2 维修员）
     */
    @TableField(value = "role_type", jdbcType = JdbcType.INTEGER)
    private Integer roleType;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(String deptNumber) {
        this.deptNumber = deptNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", deptNumber=" + deptNumber + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", roleType=" + roleType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDel=" + isDel +
                '}';
    }
}
