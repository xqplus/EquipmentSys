package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.CommonConst;
import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.ApplyForm;
import cn.xqplus.equipmentsys.model.Apply;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IApplyService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;

/**
 * 申请信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/apply", name = "申请信息相关")
public class ApplyController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IApplyService applyService;

    @GetMapping(value = "/page", name = "申请信息page")
    public PageResult<ApplyForm> page(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit, ApplyForm wrapper, String name) {
        IPage<ApplyForm> iPage = applyService.selectPage(new Page<>(page, limit), wrapper, name);
        return jr(iPage);
    }

    @GetMapping(value = "/getNextApplyNumberByDeptNumber", name = "通过部门编号获取最新的申请编号")
    public String getNextApplyNumberByDeptNumber(String deptNumber) {
        return applyService.getNextApplyNumberByDeptNumber(deptNumber);
    }

    @PostMapping(value = "/add", name = "申请新增")
    public String add(ApplyForm applyForm) {

        User currentUserInfo = userService.getCurrentUserInfo();
        Apply curUserApply = applyService.getOne(new QueryWrapper<Apply>()
                .eq("user_number", currentUserInfo.getUserNumber())
                .eq("apply_state", 0));
        if (curUserApply != null) {
            return CommonConst.EXISTS;
        }
        if (currentUserInfo.getRoleType().equals(applyForm.getApplyType())) {
            return CommonConst.CONFLICT;
        }
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
            return CommonConst.APPLY_SUCCESS;
        }
        return CommonConst.APPLY_ERROR;
    }

    @PostMapping(value = "update", name = "申请编辑")
    public String update(ApplyForm applyForm) {
        User currentUserInfo = userService.getCurrentUserInfo();
        if (currentUserInfo.getRoleType().equals(applyForm.getApplyType())) {
            return CommonConst.CONFLICT;
        }
        Apply apply = new Apply();
        try {
            BeanUtils.copyProperties(apply, applyForm);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean update = applyService.update(apply, new UpdateWrapper<Apply>()
                .eq("id", applyForm.getId()));
        return stringResult(update);
    }

    @PostMapping(value = "/delete", name = "申请删除")
    public String delete(@NotNull int id) {
        boolean removeById = applyService.removeById(id);
        return stringResult(removeById);
    }

    @GetMapping(value = "/pass", name = "审批通过")
    public String pass(ApplyForm applyForm) {
        boolean pass = applyService.passApply(applyForm);
        return stringResult(pass);
    }

    @GetMapping(value = "/reject", name = "审批驳回")
    public String reject(ApplyForm applyForm) {
        boolean rejectApply = applyService.rejectApply(applyForm);
        return stringResult(rejectApply);
    }

}
