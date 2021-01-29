package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门信息 数据层
 */

@Mapper
@Repository
public interface IDepartmentMapper extends BaseMapper<Department> {

    /**
     * 根据角色id获取部门
     * @param role
     * @return List<UserForm>
     */
    List<UserForm> getDeptByRole(String role);

    List<DepartmentForm> getList(Page<DepartmentForm> page, @Param("q") DepartmentForm departmentForm);

}
