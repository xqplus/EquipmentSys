package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IRepairMapper;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.service.IRepairService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 维修信息 服务层实现
 */

@Service
public class RepairServiceImpl implements IRepairService {

    @Autowired
    private IRepairMapper repairMapper;

    @Override
    public Repair getNextRepairNumber() {
        List<Repair> listByNumberDesc = repairMapper.getListByNumberDesc();
        Repair repair = new Repair();
        if (CollectionUtils.isNotEmpty(listByNumberDesc)) {
            String number = listByNumberDesc.get(0).getRepairNumber();
            String nextNumber = String.valueOf(Integer.parseInt(number) + 1);
            // 这里的算法限定维修编号五位数 <100000
            String[] zeros = {"", "0000", "000", "00", "0", ""};
            repair.setRepairNumber(zeros[nextNumber.length()] + nextNumber);
        } else {
            repair.setRepairNumber("00001");
        }
        return repair;
    }

    @Override
    public boolean save(Repair entity) {
        int insert = repairMapper.insert(entity);
        return (insert >= 1);
    }

    @Override
    public boolean saveBatch(Collection<Repair> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Repair> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Repair> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Repair entity) {
        return false;
    }

    @Override
    public Repair getOne(Wrapper<Repair> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Repair> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Repair> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Repair> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Repair> getEntityClass() {
        return null;
    }
}
