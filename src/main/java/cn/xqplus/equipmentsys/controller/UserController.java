package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
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
    public PageResult<UserForm> page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, UserForm wrapper){
        IPage<UserForm> iPage = userService.selectPage(new Page<>(page, limit), wrapper);
        return jr(iPage);
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

    @PostMapping(value = "/deleteBatch", name = "批量删除用户信息")
    public String deleteBatch(@NotNull @RequestParam(value = "ids[]") String[] ids) {
        /* 包含批量删除密码可见信息 */
        boolean deleteBatch = userService.deleteBatch(Arrays.asList(ids));
        return stringResult(deleteBatch);
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

    @GetMapping(value = "/exportExcel", name = "用户列表Excel导出")
    public void exportExcel(@RequestParam(value = "ids") String[] ids, HttpServletResponse response) {
        // 导出
        userService.exportExcel(Arrays.asList(ids), response);
    }
}
