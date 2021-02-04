package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.ApplyForm;
import cn.xqplus.equipmentsys.model.Apply;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IApplyService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * 申请信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/apply", name = "申请信息相关")
public class ApplyController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IApplyService applyService;

    @GetMapping(value = "/page", name = "申请信息page")
    public Object page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, ApplyForm wrapper,
                       String userName) {
        Page<ApplyForm> pages = new Page<>(page, limit);
        return applyService.selectPage(pages, wrapper, userName);
    }

    @GetMapping(value = "/getNextApplyNumberByDeptNumber", name = "通过部门编号获取最新的申请编号")
    public String getNextApplyNumberByDeptNumber(String deptNumber) {
        return applyService.getNextApplyNumberByDeptNumber(deptNumber);
    }

    @PostMapping(value = "/add", name = "申请新增")
    public String add(ApplyForm applyForm) {
        User currentUserInfo = userService.getCurrentUserInfo();
        if (currentUserInfo.getRoleType().equals(applyForm.getApplyType())) {
            return "conflict";
        } else {
            User user = userService.getOne(new QueryWrapper<User>()
                    .eq("user_name", applyForm.getUserName()));
            applyForm.setUserNumber(user.getUserNumber());
            applyForm.setApplyState(0);
            Apply apply = new Apply();
            try {
                BeanUtils.copyProperties(apply, applyForm);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            boolean save = applyService.save(apply);
            if (save) {
                return "applySuccess";
            } else {
                return "applyError";
            }
        }
    }
}
