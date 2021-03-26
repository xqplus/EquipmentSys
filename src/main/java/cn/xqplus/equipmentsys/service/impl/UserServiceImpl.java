package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IUserMapper;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.PasswordVisible;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.response.UserResp;
import cn.xqplus.equipmentsys.service.ICommonService;
import cn.xqplus.equipmentsys.service.IDepartmentService;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import cn.xqplus.equipmentsys.service.IUserService;
import cn.xqplus.equipmentsys.utils.ExcelUtils;
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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * 用户信息 服务层实现
 */

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User>
        implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IPasswordVisibleService passwordVisibleService;

    @Override
    public IPage<UserForm> selectPage(Page<UserForm> page, UserForm wrapper) {
        IPage<UserForm> iPage = userMapper.getList(page, wrapper, null);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (UserForm userForm : iPage.getRecords()) {
                // 时间转换
                userForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(userForm.getCreateTime()));
                userForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(userForm.getUpdateTime()));
            }
        }
        iPage.setTotal(iPage.getRecords().size());

        return iPage;
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean updateUser(User user) {

        // 查询原用户名
        User priUser = userMapper.selectOne(new QueryWrapper<User>()
                .eq("password", user.getPassword()));
        PasswordVisible passwordVisible = new PasswordVisible();
        passwordVisible.setUserName(user.getUserName());

        // 更新密码可见信息（用户名）
        boolean passwordVisibleUpdate = passwordVisibleService.update(passwordVisible, new UpdateWrapper<PasswordVisible>()
                .eq("user_name", priUser.getUserName()));

        // 更新用户表信息
        int updateUser = userMapper.update(user, new UpdateWrapper<User>()
                .eq("password", user.getPassword()));

        return (passwordVisibleUpdate && (updateUser >= 1));
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
    @Transactional
    public boolean deleteBatch(List<String> idList) throws RuntimeException {

        // 批量删除密码可见信息
        List<User> users = userMapper.selectBatchIds(idList);
        List<String> nameList = new ArrayList<>();
        List<String> pwdIdList = new ArrayList<>();

        for (User u : users) {
            nameList.add(u.getUserName());
        }
        List<PasswordVisible> pwds = passwordVisibleService.list(new QueryWrapper<PasswordVisible>()
                .in("user_name", nameList));
        for (PasswordVisible p : pwds) {
            pwdIdList.add(String.valueOf(p.getId()));
        }
        passwordVisibleService.removeByIds(pwdIdList);

        // 批量删除用户信息
        int deleteBatchIds = userMapper.deleteBatchIds(idList);
        return (deleteBatchIds >= 1);
    }

    @Override
    public void exportExcel(List<String> ids, HttpServletResponse response) {
        // 获取用户列表集合
        List<UserForm> userForms = userMapper.getList(new Page<>(), new UserForm(), ids).getRecords();
        List<UserResp> exportList = new ArrayList<>();

        for (UserForm u : userForms) {
            // 设置 yyyy-MM-dd 日期格式
            u.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(u.getCreateTime()));
            u.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(u.getUpdateTime()));

            UserResp userResp = new UserResp();
            try {
                BeanUtils.copyProperties(userResp, u);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            exportList.add(userResp);
        }
        ExcelUtils.exportExcel(exportList, "设备管理系统用户信息", "用户信息",
                UserResp.class, "设备管理系统用户信息-" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date().getTime()), response);
    }

}
