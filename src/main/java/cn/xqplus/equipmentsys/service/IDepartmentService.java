package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门信息 服务层
 */

public interface IDepartmentService extends IService<Department> {

    /**
     * 根据角色id获取部门
     * @param role
     * @return List<UserForm>
     */
    List<UserForm> getDeptByRole(String role);

    /**
     * 获得Department分页list
     * @param page
     * @param wrapper
     * @return
     */
    Page<DepartmentForm> selectPage(Page<DepartmentForm> page, DepartmentForm wrapper);
}
