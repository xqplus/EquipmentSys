package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由跳转 接口
 */

@Controller
@RequestMapping(value = "/equipmentSys/router", name = "路由跳转")
public class RouterController {

    @GetMapping(value = "/login", name = "登录页")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/index", name = "主页")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/userManagement", name = "用户管理")
    public String userManagement() {
        return "user/page";
    }

    @GetMapping(value = "/deptManagement", name = "部门管理")
    public String deptManagement() {
        return "department/page";
    }

    @GetMapping(value = "/equipManagement", name = "设备管理")
    public String equipManagement() {
        return "equipment/page";
    }

    @GetMapping(value = "/equipTypeManagement", name = "设备类型管理")
    public String equipTypeManagement() {
        return "equipmentType/page";
    }

    @GetMapping(value = "/repairManagement", name = "维修管理")
    public String repairManagement() {
        return "repair/page";
    }

    @GetMapping(value = "/registry", name = "注册页")
    public String registry() {
        return "registry/registry";
    }

    @GetMapping(value = "/forgotPassword", name = "密码找回")
    public String forgotPassword() {
        return "registry/forgotPassword";
    }

    @GetMapping(value = "/repairHistory", name = "维修历史记录")
    public String repairHistory() {
        return "repair/history";
    }

    @GetMapping(value = "/positionApply", name = "职位申请")
    public String positionApply() {
        return "positionApply/page";
    }
}
