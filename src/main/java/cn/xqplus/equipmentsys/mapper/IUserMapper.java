package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息 数据层
 */

@Mapper
@Repository
public interface IUserMapper extends BaseMapper<User> {

    /**
     * 获得User分页list
     * @param page
     * @param wrapper
     * @param ids id 集合
     * @return List<UserForm>
     */
    IPage<UserForm> getList(Page<UserForm> page, @Param("q") UserForm wrapper, @Param("ids") List<String> ids);

}
