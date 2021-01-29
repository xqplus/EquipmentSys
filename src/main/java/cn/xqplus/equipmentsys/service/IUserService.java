package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 用户信息 服务层
 */

public interface IUserService extends IService<User> {


    /**
     * 保存用户
     * @param user
     * @return boolean
     */
    boolean saveUser(User user);

    /**
     * 根据wrapper条件查询用户
     * @return User
     */
    List<User> findByWrapper(QueryWrapper<User> wrapper);

    /**
     * 修改用户
     * @param user wrapper
     * @return boolean
     */
    boolean updateUser(User user, UpdateWrapper<User> wrapper);

    /**
     * 获得User分页list
     * @param page
     * @param wrapper
     * @return
     */
    Page<UserForm> selectPage(Page<UserForm> page, UserForm wrapper);
}
