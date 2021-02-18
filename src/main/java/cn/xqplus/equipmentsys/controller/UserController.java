package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息管理 接口
 */
@RestController
@RequestMapping(value = "/equipmentSys/user", name = "用户信息相关")
public class UserController extends BaseController {

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
        /* 用户保存包含密码可见信息保存 */
        boolean save = userService.saveUser(user);
        return stringResult(save);
    }

    @PostMapping(value = "/update", name = "更新用户信息")
    public String update(User user) {
        /* 用户更新包含密码可见信息更新 */
        boolean updateUser = userService.updateUser(user);
        return stringResult(updateUser);
    }

    @PostMapping(value = "/delete", name = "删除用户信息")
    public String delete(@NotNull long id) {
        /* 包含删除密码可见信息 */
        boolean removeById = userService.removeById(id);
        return stringResult(removeById);
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

    @GetMapping(value = "/getCurrentUserInfo", name = "当前登录用户信息")
    public User getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }
}
