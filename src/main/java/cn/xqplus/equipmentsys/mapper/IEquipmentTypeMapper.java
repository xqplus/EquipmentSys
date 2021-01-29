package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.EquipmentType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备类型信息 数据层
 */

@Mapper
@Repository
public interface IEquipmentTypeMapper extends BaseMapper<EquipmentType> {

    /**
     * 表单list
     * @param page 分页
     * @param wrapper 条件
     * @return List<EquipmentTypeForm>
     */
    List<EquipmentTypeForm> getList(Page<EquipmentTypeForm> page, @Param("q") EquipmentTypeForm wrapper);

    /**
     * 获取列表根据编号降序排列
     * @return List<Equipment>
     */
    List<EquipmentType> getListByNumberDesc();
}
