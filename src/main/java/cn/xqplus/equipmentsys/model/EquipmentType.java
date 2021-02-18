package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.BusinessEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 设备类型信息 对应表 t_equipment_type
 */

@Data
@TableName("t_equipment_type")
public class EquipmentType extends BusinessEntity implements Serializable {

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

}
