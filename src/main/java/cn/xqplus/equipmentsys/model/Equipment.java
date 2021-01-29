package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 设备信息 对应表 t_equipment
 */

@Data
@TableName("t_equipment")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备编号
     */
    @TableField(value = "equip_number", jdbcType = JdbcType.VARCHAR)
    private String equipNumber;

    /**
     * 设备类型编号
     */
    @TableField(value = "equip_type_number", jdbcType = JdbcType.VARCHAR)
    private String equipTypeNumber;

    /**
     * 设备名称
     */
    @TableField(value = "equip_name", jdbcType = JdbcType.VARCHAR)
    private String equipName;

    /**
     * 设备概述
     */
    @TableField(value = "equip_summary", jdbcType = JdbcType.VARCHAR)
    private String equipSummary;

    /**
     * 设备状态 0 使用中 1 维修中 2 已报废
     */
    @TableField(value = "equip_state", jdbcType = JdbcType.INTEGER)
    //@NotNull 当查询没有输入当前值时会报错 设置默认值sql会默认填充
    private Integer equipState;

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
