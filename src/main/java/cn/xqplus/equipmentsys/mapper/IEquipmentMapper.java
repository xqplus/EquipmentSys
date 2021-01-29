package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.model.Equipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备信息 数据层
 */

@Mapper
@Repository
public interface IEquipmentMapper extends BaseMapper<Equipment> {

    /**
     * 表单list
     * @param page 分页
     * @param wrapper 条件
     * @return List<EquipmentForm>
     */
    List<EquipmentForm> getList(Page<EquipmentForm> page, @Param("q") EquipmentForm wrapper);

    List<Equipment> getListByNumberDesc();
}
