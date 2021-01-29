package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.PasswordVisible;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息管理 接口
 */
@RestController
@RequestMapping(value = "/equipmentSys/user", name = "用户信息相关")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPasswordVisibleService passwordVisibleService;

    @GetMapping(value = "/page", name = "用户信息page")
    public Object list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, UserForm wrapper){
        Page<UserForm> pages = new Page<>(page, limit);
        return userService.selectPage(pages, wrapper);
    }

    @PostMapping(value = "/add", name = "注册,用户信息新增")
    public String add(User user) {
        // 将没有加密的密码存在t_password_visible中
        PasswordVisible passwordVisible = new PasswordVisible();
        passwordVisible.setUserName(user.getUserName());
        passwordVisible.setPasswordVisible(user.getPassword());
        // 保存可见密码信息
        passwordVisibleService.save(passwordVisible);
        // 查询部门人员根据用户编号排序
        List<User> userListDesc = userService.findByWrapper(new QueryWrapper<User>()
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
        boolean save = userService.saveUser(user);
        if (save) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/update", name = "更新用户信息")
    public String update(User user) {
        // 查询用户原用户名
        User priUser = userService.getOne(new QueryWrapper<User>()
                .eq("password", user.getPassword()));
        PasswordVisible passwordVisible = new PasswordVisible();
        passwordVisible.setUserName(user.getUserName());
        // 更新密码可见信息（用户名）
        passwordVisibleService.updatePasswordVisible(passwordVisible, new UpdateWrapper<PasswordVisible>()
                .eq("user_name", priUser.getUserName()));
        // 更新用户表信息
        boolean f = userService.updateUser(user, new UpdateWrapper<User>()
                .eq("password", user.getPassword()));
        if (f) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/delete", name = "删除用户信息")
    public String delete(@NotNull long id) {
        // 删除用户信息
        boolean b = userService.removeById(id);
        // 删除密码可见信息 正常情况下password_visible和user id一致
        passwordVisibleService.removeById(id);
        if (b) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/userCheck", name = "用户是否存在（用户名是否可用）")
    public String userCheck(String userName) {
        List<User> userList = userService.findByWrapper(new QueryWrapper<User>()
                .eq("user_name", userName));
        if (CollectionUtils.isNotEmpty(userList)) {
            return "exists";
        } else {
            return "noExists";
        }
    }

}
