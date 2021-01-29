package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IRoleDeptMapper;
import cn.xqplus.equipmentsys.model.RoleDept;
import cn.xqplus.equipmentsys.service.IRoleDeptService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * 角色部门关联信息 服务层实现
 */

@Service
public class RoleDeptServiceImpl implements IRoleDeptService {

    @Autowired
    private IRoleDeptMapper roleDeptMapper;

    @Override
    public boolean save(RoleDept entity) {
        int insert = roleDeptMapper.insert(entity);
        return (insert >= 1);
    }

    @Override
    public boolean update(RoleDept entity, Wrapper<RoleDept> updateWrapper) {
        int update = roleDeptMapper.update(entity, updateWrapper);
        return (update >= 1);
    }

    @Override
    public boolean remove(Wrapper<RoleDept> queryWrapper) {
        int delete = roleDeptMapper.delete(queryWrapper);
        return (delete >= 1);
    }

    @Override
    public boolean saveBatch(Collection<RoleDept> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<RoleDept> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<RoleDept> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(RoleDept entity) {
        return false;
    }

    @Override
    public RoleDept getOne(Wrapper<RoleDept> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<RoleDept> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<RoleDept> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<RoleDept> getBaseMapper() {
        return null;
    }

    @Override
    public Class<RoleDept> getEntityClass() {
        return null;
    }
}
