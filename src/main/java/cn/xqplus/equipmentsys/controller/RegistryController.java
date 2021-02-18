package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.model.PasswordVisible;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 注册找回 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/registry", name = "注册&密码相关")
public class RegistryController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPasswordVisibleService passwordVisibleService;

    @PostMapping(value = "/resetPasswordCheck", name = "重置密码校验")
    public String resetPasswordCheck(User user) {
        // 校验用户信息是否一致,校验传过来信息是否是该用户名的信息
        List<User> users = userService.findByWrapper(new QueryWrapper<User>()
                .eq("user_name", user.getUserName())
                .eq("true_name", user.getTrueName())
                .eq("role_type", user.getRoleType())
                .eq("dept_number", user.getDeptNumber()));
        // 信息验证成功
        if (CollectionUtils.isNotEmpty(users)) {
            // 将更新的未加密的密码存在密码可见表t_password_visible中
            PasswordVisible passwordVisible = new PasswordVisible();
            passwordVisible.setPasswordVisible(user.getPassword());
            // 更新密码可见信息
            passwordVisibleService.updatePasswordVisible(passwordVisible, new UpdateWrapper<PasswordVisible>()
                    .eq("user_name", user.getUserName()));
            // SQL优化
            User updateUser = new User();
            // 密码加密BCryptPasswordEncoder
            updateUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            boolean f = userService.update(updateUser, new UpdateWrapper<User>()
                    .eq("user_name", user.getUserName()));
            if (f) {
                return "success";
            } else {
                return "error";
            }
        } else {
            return "noMatch";
        }
    }


}
