package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.model.PasswordVisible;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 可见密码信息 数据层
 */

@Mapper
@Repository
public interface IPasswordVisibleMapper extends BaseMapper<PasswordVisible> {

}
