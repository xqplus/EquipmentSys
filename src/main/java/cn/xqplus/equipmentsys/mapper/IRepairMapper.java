package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.form.RepairForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 维修信息 数据层
 */

@Mapper
@Repository
public interface IRepairMapper extends BaseMapper<Repair> {

    /**
     * 获取列表根据编号降序排列
     * @return List<Equipment>
     */
    List<Repair> getListByNumberDesc();

    /**
     * 表单list
     * @param page 分页
     * @param wrapper 条件
     * @return List<RepairForm>
     */
    List<RepairForm> getList(Page<RepairForm> page, @Param("q") RepairForm wrapper);
}
