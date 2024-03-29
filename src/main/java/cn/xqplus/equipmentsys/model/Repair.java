package cn.xqplus.equipmentsys.model;

import cn.xqplus.equipmentsys.Entity.LogicDeleteEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 维修信息 对应表 t_repair
 */

@Data
@TableName("t_repair")
public class Repair extends LogicDeleteEntity implements Serializable {

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
     * 故障描述
     */
    @TableField(value = "fault_remark", jdbcType = JdbcType.VARCHAR)
    private String faultRemark;

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
     * 维修日志
     */
    @TableField(value = "repair_log", jdbcType = JdbcType.VARCHAR)
    private String repairLog;

    /**
     * 维修时间（更新时间，严格更新时插入）
     */
    @TableField(value = "repair_time", jdbcType = JdbcType.DECIMAL, fill = FieldFill.UPDATE)
    private Long repairTime;

    /**
     * 维修状态 0 维修完成 1 维修中 2 报废处理 默认为维修中
     */
    @TableField(value = "repair_state", jdbcType = JdbcType.INTEGER)
    private Integer repairState = 1;

}
