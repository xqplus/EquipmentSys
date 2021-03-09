package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IPasswordVisibleMapper;
import cn.xqplus.equipmentsys.model.PasswordVisible;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 可见密码信息 服务层实现
 */

@Service
public class PasswordVisibleImpl implements IPasswordVisibleService {

    @Autowired
    private IPasswordVisibleMapper passwordVisibleMapper;

    @Override
    public boolean updatePasswordVisible(PasswordVisible passwordVisible, UpdateWrapper<PasswordVisible> wrapper) {
        int update = passwordVisibleMapper.update(passwordVisible, wrapper);
        return (update >= 1);
    }

    @Override
    public boolean save(PasswordVisible entity) {
        int insert = passwordVisibleMapper.insert(entity);
        return insert >= 1;
    }

    @Override
    public boolean removeById(Serializable id) {
        int i = passwordVisibleMapper.deleteById(id);
        return (i >= 1);
    }

    @Override
    public boolean remove(Wrapper<PasswordVisible> queryWrapper) {
        int delete = passwordVisibleMapper.delete(queryWrapper);
        return (delete >= 1);
    }

    @Override
    public List<PasswordVisible> list(Wrapper<PasswordVisible> queryWrapper) {
        return passwordVisibleMapper.selectList(queryWrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        int deleteBatchIds = passwordVisibleMapper.deleteBatchIds(idList);
        return (deleteBatchIds >= 1);
    }

    @Override
    public boolean saveBatch(Collection<PasswordVisible> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<PasswordVisible> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<PasswordVisible> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(PasswordVisible entity) {
        return false;
    }

    @Override
    public PasswordVisible getOne(Wrapper<PasswordVisible> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<PasswordVisible> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<PasswordVisible> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<PasswordVisible> getBaseMapper() {
        return null;
    }

    @Override
    public Class<PasswordVisible> getEntityClass() {
        return null;
    }


}
