package cn.xqplus.equipmentsys.model;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 维修信息 对应表 t_repair
 */

@Data
@TableName("t_repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 维修编号
     */
    @TableField(value = "repair_number", jdbcType = JdbcType.VARCHAR)
    private String repairNumber;

    /**
     * 设备编号（当前维修设备）
     */
    @TableField(value = "equip_number", jdbcType = JdbcType.VARCHAR)
    private String equipNumber;

    /**
     * 报修人编号
     */
    @TableField(value = "reporter_number", jdbcType = JdbcType.VARCHAR)
    private String reporterNumber;

    /**
     * 报修时间（创建时间）
     */
    @TableField(value = "report_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.INSERT)
    private Long reportTime;

    /**
     * 维修人编号
     */
    @TableField(value = "repairer_number", jdbcType = JdbcType.VARCHAR)
    private String repairerNumber;

    /**
     * 维修时间（更新时间，严格更新时插入）
     */
    @TableField(value = "repair_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.UPDATE)
    private Long repairTime;

    /**
     * 维修是否结束 0 未结束 1 结束
     */
    @TableField(value = "is_end", jdbcType = JdbcType.INTEGER)
    private Integer isEnd = 0;

    /**
     * 逻辑删除（0 存在 1 删除）
     */
    @TableField(value = "is_del", jdbcType = JdbcType.INTEGER)
    @TableLogic
    @NotNull
    private Integer isDel = 0;

}
