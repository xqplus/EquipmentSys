package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.BusinessEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 设备信息 对应表 t_equipment
 */

@Data
@TableName("t_equipment")
public class Equipment extends BusinessEntity implements Serializable {

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

}
