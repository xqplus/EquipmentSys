package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.mapper.IDepartmentMapper;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import cn.xqplus.equipmentsys.model.RoleDept;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.response.UserResp;
import cn.xqplus.equipmentsys.service.IDepartmentService;
import cn.xqplus.equipmentsys.service.IRoleDeptService;
import cn.xqplus.equipmentsys.service.IUserService;
import cn.xqplus.equipmentsys.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentMapper departmentMapper;

    @Autowired
    private IRoleDeptService roleDeptService;

    @Autowired
    private IUserService userService;

    @Override
    public Page<DepartmentForm> selectPage(Page<DepartmentForm> page, DepartmentForm wrapper) {
        List<DepartmentForm> departmentForms = departmentMapper.getList(page, wrapper);
        if (CollectionUtils.isNotEmpty(departmentForms)) {
            for (DepartmentForm departmentForm : departmentForms) {
                // 时间转换
                departmentForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(departmentForm.getCreateTime()));
                departmentForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(departmentForm.getUpdateTime()));
            }
        }
        // 设置返回状态码
        page.setMaxLimit(0L);
        // 设置msg
        page.setCountId("success");
        page.setRecords(departmentForms);
        page.setTotal(departmentForms.size());
        return page;
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
            int update = departmentMapper.update(department, new UpdateWrapper<Department>()
                    .eq("dept_number", dept.getDeptNumber()));
            if (update >= 1) {
                return "success";
            } else {
                return "error";
            }
        }
    }

    @Override
    @Transactional
    public String deleteById(int id) throws RuntimeException {
        // 判断部门下是否有用户
        Department department = departmentMapper.selectById(id);
        List<User> users = userService.findByWrapper(new QueryWrapper<User>()
                .eq("dept_number", department.getDeptNumber()));
        if (users.size() > 0) {
            return "existsUser";
        } else {
            // 删除相应角色部门关联信息（根据部门编号删除）
            roleDeptService.remove(new QueryWrapper<RoleDept>()
                    .eq("dept_number", department.getDeptNumber()));
            // 删除部门信息
            int deleteById = departmentMapper.deleteById(id);
            if (deleteById >= 1) {
                return "success";
            } else {
                return "error";
            }
        }
    }

    @Override
    public Department getById(Serializable id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public boolean save(Department entity) {
        int insert = departmentMapper.insert(entity);
        return (insert >= 1);
    }

    @Override
    public boolean update(Department entity, Wrapper<Department> updateWrapper) {
        int update = departmentMapper.update(entity, updateWrapper);
        return (update >= 1);
    }

    @Override
    public boolean removeById(Serializable id) {
        int deleteById = departmentMapper.deleteById(id);
        return (deleteById >= 1);
    }

    @Override
    public void exportDeptExcel(List<String> ids, HttpServletResponse response) {
//        List<UserForm> list = userMapper.getList(null, new UserForm(), ids);
//        List<UserResp> exportList = new ArrayList<>();
//
//        for (UserForm u : list) {
//            // 设置 yyyy-MM-dd 日期格式
//            u.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(u.getCreateTime()));
//            u.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(u.getUpdateTime()));
//
//            UserResp userResp = new UserResp();
//            try {
//                BeanUtils.copyProperties(userResp, u);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            exportList.add(userResp);
//        }
//        ExcelUtils.exportExcel(exportList, "设备管理系统用户信息", "用户信息",
//                UserResp.class, "设备管理系统用户信息-" + new SimpleDateFormat("yyyy-MM-dd")
//                        .format(new Date().getTime()), response);
    }

    @Override
    public boolean saveBatch(Collection<Department> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Department> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Department> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Department entity) {
        return false;
    }

    @Override
    public Department getOne(Wrapper<Department> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Department> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Department> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Department> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Department> getEntityClass() {
        return null;
    }

}
