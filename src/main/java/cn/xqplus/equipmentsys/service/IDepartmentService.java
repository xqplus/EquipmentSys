package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 部门信息 服务层
 */

public interface IDepartmentService extends IService<Department>, ICommonService {

    /**
     * 根据角色id获取部门
     * @param role
     * @return List<UserForm>
     */
    List<UserForm> getDeptByRole(String role);

    /**
     * Department分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage<DepartmentForm> selectPage(Page<DepartmentForm> page, DepartmentForm wrapper);

    /**
     * 通过角色获取下一个部门信息
     * @param role
     * @return
     */
    Object getNextDeptByRole(String role);

    /**
     * 部门新增
     * @param departmentForm
     * @return
     */
    boolean add(DepartmentForm departmentForm);

    /**
     * 部门编辑
     * @param departmentForm
     * @return
     */
    String updateDept(DepartmentForm departmentForm);

    /**
     * 根据 id 删除
     * @param id
     * @return
     */
    String deleteDeptById(int id);

}
