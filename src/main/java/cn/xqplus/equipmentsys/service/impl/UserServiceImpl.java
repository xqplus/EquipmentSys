package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IUserMapper;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.PasswordVisible;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 用户信息 服务层实现
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IPasswordVisibleService passwordVisibleService;

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    @Transactional // 事务
    public boolean saveUser(User user) {
        // 将没有加密的密码存在t_password_visible中
        PasswordVisible passwordVisible = new PasswordVisible();
        passwordVisible.setUserName(user.getUserName());
        passwordVisible.setPasswordVisible(user.getPassword());
        // 保存可见密码信息
        boolean passwordVisibleSave = passwordVisibleService.save(passwordVisible);
        // 查询部门人员根据用户编号排序
        List<User> userListDesc = userMapper.selectList(new QueryWrapper<User>()
                .eq("dept_number", user.getDeptNumber())
                .orderByDesc("user_number"));
        if (CollectionUtils.isNotEmpty(userListDesc)) {
            // 设置用户编号
            String userNum = String.valueOf(Integer.parseInt(userListDesc.get(0).getUserNumber()) + 1);
            user.setUserNumber(userNum);
        } else {
            user.setUserNumber(user.getDeptNumber() + "001");
        }
        // 密码加密 加密方式为BCryptPasswordEncoder
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int userSave = userMapper.insert(user);

        return (passwordVisibleSave && (userSave >= 1));
    }

    @Override
    public List<User> findByWrapper(QueryWrapper<User> wrapper) {
        return userMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        // 查询原用户名
        User priUser = userMapper.selectOne(new QueryWrapper<User>()
                .eq("password", user.getPassword()));
        PasswordVisible passwordVisible = new PasswordVisible();
        passwordVisible.setUserName(user.getUserName());
        // 更新密码可见信息（用户名）
        boolean passwordVisibleUpdate = passwordVisibleService.updatePasswordVisible(passwordVisible, new UpdateWrapper<PasswordVisible>()
                .eq("user_name", priUser.getUserName()));

        // 更新用户表信息
        int updateUser = userMapper.update(user, new UpdateWrapper<User>()
                .eq("password", user.getPassword()));

        return (passwordVisibleUpdate && (updateUser >= 1));
    }

    @Override
    public Page<UserForm> selectPage(Page<UserForm> page, UserForm wrapper) {
        List<UserForm> userForms = userMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(userForms)) {
            for (UserForm userForm : userForms) {
                // 时间转换
                userForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(userForm.getCreateTime()));
                userForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(userForm.getUpdateTime()));
            }
        }
        // 设置返回状态码
        page.setMaxLimit(0L);
        // 设置msg
        page.setCountId("success");
        page.setRecords(userForms);
        page.setTotal(userForms.size());
        return page;
    }

    @Override
    public User getCurrentUserInfo() {
        org.springframework.security.core.userdetails.User currentUser =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("user_name", currentUser.getUsername()));
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper) {
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public boolean removeById(Serializable id) throws RuntimeException {
        // 删除密码可见信息
        User user = userMapper.selectById(id);
        boolean passwordVisibleDelete = passwordVisibleService.remove(new QueryWrapper<PasswordVisible>()
                .eq("user_name", user.getUserName()));
        // 删除用户信息
        int userDelete = userMapper.deleteById(id);

        return (passwordVisibleDelete && (userDelete >= 1));
    }

    @Override
    public boolean update(User entity, Wrapper<User> updateWrapper) {
        int update = userMapper.update(entity, updateWrapper);
        return (update >= 1);
    }

    @Override
    public boolean saveBatch(Collection<User> entityList) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<User> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(User entity) {
        return false;
    }

    @Override
    public boolean update(Wrapper<User> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        return false;
    }

    @Override
    public User getById(Serializable id) {
        return null;
    }

    @Override
    public List<User> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<User> listByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int count(Wrapper<User> queryWrapper) {
        return 0;
    }

    @Override
    public List<User> list(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public <E extends IPage<User>> E page(E page, Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <E extends IPage<User>> E page(E page) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return null;
    }

    @Override
    public List<Object> listObjs() {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return null;
    }

    @Override
    public BaseMapper<User> getBaseMapper() {
        return null;
    }

    @Override
    public Class<User> getEntityClass() {
        return null;
    }

    @Override
    public QueryChainWrapper<User> query() {
        return null;
    }

    @Override
    public LambdaQueryChainWrapper<User> lambdaQuery() {
        return null;
    }

    @Override
    public KtQueryChainWrapper<User> ktQuery() {
        return null;
    }

    @Override
    public KtUpdateChainWrapper<User> ktUpdate() {
        return null;
    }

    @Override
    public UpdateChainWrapper<User> update() {
        return null;
    }

    @Override
    public LambdaUpdateChainWrapper<User> lambdaUpdate() {
        return null;
    }

    @Override
    public boolean saveOrUpdate(User entity, Wrapper<User> updateWrapper) {
        return false;
    }
}
