package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import cn.xqplus.equipmentsys.model.RoleDept;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IDepartmentService;
import cn.xqplus.equipmentsys.service.IRoleDeptService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 部门信息管理 接口
 */
@RestController
@RequestMapping(value = "/equipmentSys/department", name = "部门信息相关")
public class DepartmentController {

//    Logger logger = LoggerFactory.getLogger(Department.class);

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleDeptService roleDeptService;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/getDeptByRole", name = "通过角色获取部门")
    public Object getDeptByRole(String role) {
        return departmentService.getDeptByRole(role);
    }

    @GetMapping(value = "/getNextDeptByRole", name = "获取下一个部门信息")
    public Object getNextDeptByRole(String role) {
        List<UserForm> userForms = departmentService.getDeptByRole(role);
        Department department = new Department();
        if (userForms.size() > 0) {
            // 获取最后一个，正常情况（没排序）下是最新的
            UserForm last = userForms.get(userForms.size() - 1);
            String deptNumber = String.valueOf(Integer.parseInt(last.getDeptNumber()) + 1);
            String temp = String.valueOf(Integer.parseInt(last.getDeptName().substring(2, 3)) + 1);
            // 设置新的部门信息
            department.setDeptNumber(deptNumber);
            department.setDeptName(last.getDeptName().substring(0,2) + temp + "部");
        }
        return department;
    }

    @GetMapping(value = "/page", name = "部门信息page")
    public Object list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, DepartmentForm wrapper){
        Page<DepartmentForm> pages = new Page<>(page, limit);
        return departmentService.selectPage(pages, wrapper);
    }

    @PostMapping(value = "/add", name = "新增")
    public String add(DepartmentForm departmentForm) {
        // 保存角色部门关联信息
        RoleDept roleDept = new RoleDept();
        roleDept.setRoleType(departmentForm.getRoleType());
        roleDept.setDeptNumber(departmentForm.getDeptNumber());
        roleDeptService.save(roleDept);
        // 保存部门信息
        Department department = new Department();
        department.setDeptNumber(departmentForm.getDeptNumber());
        department.setDeptName(departmentForm.getDeptName());
        department.setDeptIntroduce(departmentForm.getDeptIntroduce());
        boolean save = departmentService.save(department);
        if (save) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/update", name = "部门信息变更")
    public String update(DepartmentForm departmentForm) {
        // 查询原部门信息
        Department dept = departmentService.getById(departmentForm.getId());
        List<User> users = userService.findByWrapper(new QueryWrapper<User>()
                .eq("dept_number", dept.getDeptNumber()));
        // 部门下有用户不能更改
        if (users.size() > 0) {
            return "existsUser";
        } else {
            RoleDept roleDept = new RoleDept();
            roleDept.setRoleType(departmentForm.getRoleType());
            roleDept.setDeptNumber(departmentForm.getDeptNumber());
            // 更新角色部门关联信息
            roleDeptService.update(roleDept, new UpdateWrapper<RoleDept>()
                    .eq("dept_number", dept.getDeptNumber()));
            Department department = new Department();
            try {
                BeanUtils.copyProperties(department, departmentForm);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            // 更新部门信息
            boolean update = departmentService.update(department, new UpdateWrapper<Department>()
                    .eq("dept_number", dept.getDeptNumber()));
            if (update) {
                return "success";
            } else {
                return "error";
            }
        }
    }

    @PostMapping(value = "/delete", name = "删除部门信息")
    public String delete(@NotNull int id) {
        // 判断部门下是否有用户
        Department department = departmentService.getById(id);
        List<User> users = userService.findByWrapper(new QueryWrapper<User>()
                .eq("dept_number", department.getDeptNumber()));
        if (users.size() > 0) {
            return "existsUser";
        } else {
            // 删除相应角色部门关联信息（根据部门编号删除）
            roleDeptService.remove(new QueryWrapper<RoleDept>()
                    .eq("dept_number", department.getDeptNumber()));
            // 删除部门信息
            boolean b = departmentService.removeById(id);
            if (b) {
                return "success";
            } else {
                return "error";
            }
        }
    }
}
