package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
}
