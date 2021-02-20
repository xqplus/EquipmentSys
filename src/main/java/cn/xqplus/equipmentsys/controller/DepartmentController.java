package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 部门信息管理 接口
 */
@RestController
@RequestMapping(value = "/equipmentSys/department", name = "部门信息相关")
public class DepartmentController extends BaseController {

//    Logger logger = LoggerFactory.getLogger(Department.class);

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping(value = "/getDeptByRole", name = "通过角色获取部门")
    public Object getDeptByRole(String role) {
        return departmentService.getDeptByRole(role);
    }

    @GetMapping(value = "/getNextDeptByRole", name = "获取下一个部门信息")
    public Object getNextDeptByRole(String role) {
        return departmentService.getNextDeptByRole(role);
    }

    @GetMapping(value = "/page", name = "部门信息page")
    public Object list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, DepartmentForm wrapper){
        Page<DepartmentForm> pages = new Page<>(page, limit);
        return departmentService.selectPage(pages, wrapper);
    }

    @PostMapping(value = "/add", name = "新增")
    public String add(DepartmentForm departmentForm) {
        boolean add = departmentService.add(departmentForm);
        return stringResult(add);
    }

    @PostMapping(value = "/update", name = "部门信息变更")
    public String update(DepartmentForm departmentForm) {
        return departmentService.updateDept(departmentForm);
    }

    @PostMapping(value = "/delete", name = "删除部门信息")
    public String delete(@NotNull int id) {
        return departmentService.deleteById(id);
    }

}
