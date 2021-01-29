package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.DepartmentForm;
import cn.xqplus.equipmentsys.mapper.IDepartmentMapper;
import cn.xqplus.equipmentsys.form.UserForm;
import cn.xqplus.equipmentsys.model.Department;
import cn.xqplus.equipmentsys.service.IDepartmentService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 部门信息 服务层实现
 */

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentMapper departmentMapper;

    @Override
    public List<UserForm> getDeptByRole(String role) {
        return departmentMapper.getDeptByRole(role);
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
        int i = departmentMapper.deleteById(id);
        return (i >= 1);
    }

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
