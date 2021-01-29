package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色（权限）信息 数据层
 */

@Mapper
@Repository
public interface IRoleMapper extends BaseMapper<Role> {

}
