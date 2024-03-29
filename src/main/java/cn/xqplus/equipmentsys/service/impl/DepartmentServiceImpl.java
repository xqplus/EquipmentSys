package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.ext.CommonConst;
import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.mapper.IDepartmentMapper;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import cn.xqplus.equipmentsys.model.RoleDept;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.response.DepartmentResp;
import cn.xqplus.equipmentsys.response.UserResp;
import cn.xqplus.equipmentsys.service.ICommonService;
import cn.xqplus.equipmentsys.service.IDepartmentService;
import cn.xqplus.equipmentsys.service.IRoleDeptService;
import cn.xqplus.equipmentsys.service.IUserService;
import cn.xqplus.equipmentsys.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * 部门信息 服务层实现
 */

@Service
public class DepartmentServiceImpl extends ServiceImpl<IDepartmentMapper, Department>
        implements IDepartmentService {

    @Autowired
    private IDepartmentMapper departmentMapper;

    @Autowired
    private IRoleDeptService roleDeptService;

    @Autowired
    private IUserService userService;

    @Override
    public IPage<DepartmentForm> selectPage(Page<DepartmentForm> page, DepartmentForm wrapper) {

        IPage<DepartmentForm> iPage = departmentMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (DepartmentForm departmentForm : iPage.getRecords()) {
                // 时间转换
                departmentForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(departmentForm.getCreateTime()));
                departmentForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(departmentForm.getUpdateTime()));
            }
        }
        iPage.setTotal(iPage.getRecords().size());

        return iPage;
    }

    @Override
    public List<UserForm> getDeptByRole(String role) {
        return departmentMapper.getDeptByRole(role);
    }

    @Override
    public Object getNextDeptByRole(String role) {
        List<UserForm> userForms = departmentMapper.getDeptByRole(role);
        Department department = new Department();
        if (userForms.size() > 0) {
            UserForm first = userForms.get(0);
            String deptNumber = String.valueOf(Integer.parseInt(first.getDeptNumber()) + 1);
            String temp = String.valueOf(Integer.parseInt(first.getDeptName().substring(2, 3)) + 1);
            // 设置新的部门信息
            department.setDeptNumber(deptNumber);
            department.setDeptName(first.getDeptName().substring(0,2) + temp + "部");
        }
        return department;
    }

    @Override
    @Transactional
    public boolean add(DepartmentForm departmentForm) {
        // 保存角色部门关联信息
        RoleDept roleDept = new RoleDept();
        roleDept.setRoleType(departmentForm.getRoleType());
        roleDept.setDeptNumber(departmentForm.getDeptNumber());
        boolean roleDeptSave = roleDeptService.save(roleDept);
        // 保存部门信息
        Department department = new Department();
        department.setDeptNumber(departmentForm.getDeptNumber());
        department.setDeptName(departmentForm.getDeptName());
        department.setDeptIntroduce(departmentForm.getDeptIntroduce());
        int insert = departmentMapper.insert(department);

        return (roleDeptSave && (insert >= 1));
    }

    @Override
    @Transactional
    public String updateDept(DepartmentForm departmentForm) throws RuntimeException {
        // 查询原部门信息
        Department dept = departmentMapper.selectById(departmentForm.getId());
        List<User> users = userService.list(new QueryWrapper<User>()
                .eq("dept_number", dept.getDeptNumber()));
        // 部门下有用户不能更改
        if (users.size() > 0) {
            return CommonConst.EXISTS_USER;
        }
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
        int update = departmentMapper.update(department, new UpdateWrapper<Department>()
                .eq("dept_number", dept.getDeptNumber()));
        if (update >= 1) {
            return CommonConst.SUCCESS;
        }
        return CommonConst.ERROR;
    }

    @Override
    @Transactional
    public String deleteDeptById(int id) throws RuntimeException {
        // 判断部门下是否有用户
        Department department = departmentMapper.selectById(id);
        List<User> users = userService.list(new QueryWrapper<User>()
                .eq("dept_number", department.getDeptNumber()));
        if (users.size() > 0) {
            return CommonConst.EXISTS_USER;
        }
        // 删除相应角色部门关联信息（根据部门编号删除）
        roleDeptService.remove(new QueryWrapper<RoleDept>()
                .eq("dept_number", department.getDeptNumber()));
        // 删除部门信息
        int deleteById = departmentMapper.deleteById(id);
        if (deleteById >= 1) {
            return CommonConst.SUCCESS;
        }
        return CommonConst.ERROR;
    }

    @Override
    public void exportExcel(List<String> ids, HttpServletResponse response) {

        List<DepartmentForm> departmentFormList = departmentMapper
                .getList(new Page<>(), new DepartmentForm())
                .getRecords();
        List<DepartmentResp> exportList = new ArrayList<>();

        for (DepartmentForm df : departmentFormList) {
            // 时间转换
            df.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(df.getCreateTime()));
            df.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(df.getUpdateTime()));

            DepartmentResp resp = new DepartmentResp();
            try {
                BeanUtils.copyProperties(resp, df);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            exportList.add(resp);
        }
        ExcelUtils.exportExcel(exportList, "设备管理系统部门信息", "部门信息",
                DepartmentResp.class, "设备管理系统部门信息-" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date().getTime()), response);
    }

}
