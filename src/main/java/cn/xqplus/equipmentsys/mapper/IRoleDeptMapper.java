package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.model.RoleDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色部门关联信息 数据层
 */

@Mapper
@Repository
public interface IRoleDeptMapper extends BaseMapper<RoleDept> {

}
