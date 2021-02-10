package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 设备类型信息 对应表 t_equipment_type
 */

@Data
@TableName("t_equipment_type")
public class EquipmentType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备类型编号
     */
    @TableField(value = "equip_type_number", jdbcType = JdbcType.VARCHAR)
    private String equipTypeNumber;

    /**
     * 设备类型名称
     */
    @TableField(value = "equip_type_name", jdbcType = JdbcType.VARCHAR)
    private String equipTypeName;

    /**
     * 设备类型概述
     */
    @TableField(value = "equip_type_summary", jdbcType = JdbcType.VARCHAR)
    private String equipTypeSummary;

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
