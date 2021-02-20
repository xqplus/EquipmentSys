package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.form.ApplyForm;
import cn.xqplus.equipmentsys.mapper.IApplyMapper;
import cn.xqplus.equipmentsys.model.Apply;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IApplyService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 申请信息 服务层实现
 */

@Service
public class ApplyServiceImpl implements IApplyService {

    @Autowired
    private IApplyMapper applyMapper;

    @Autowired
    private IUserService userService;

    @Override
    public Page<ApplyForm> selectPage(Page<ApplyForm> page, ApplyForm wrapper, String userName) {
        // 当前用户不是管理员时只能查看自己的申请信息
        if (userService.getCurrentUserInfo().getRoleType() != 0) {
            userName = userService.getCurrentUserInfo().getUserName();
        }
        List<ApplyForm> applyForms = applyMapper.getList(page, wrapper, userName);
        if (CollectionUtils.isNotEmpty(applyForms)) {
            for (ApplyForm applyForm : applyForms) {
                // 申请类型转换
                if (applyForm.getApplyType() == 0) {
                    applyForm.setApplyTypeName("管理员");
                }
                if (applyForm.getApplyType() == 1) {
                    applyForm.setApplyTypeName("维修员");
                }
                // 申请状态转换
                if (applyForm.getApplyState() == 0) {
                    applyForm.setApplyStateName("审批中");
                }
                if (applyForm.getApplyState() == 1) {
                    applyForm.setApplyStateName("审批通过");
                }
                if (applyForm.getApplyState() == 2) {
                    applyForm.setApplyStateName("审批驳回");
                }
                // 时间转换
                if (applyForm.getCreateTime() != null) {
                    applyForm.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(applyForm.getCreateTime()));
                }
                if (applyForm.getUpdateTime() != null) {
                    applyForm.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(applyForm.getUpdateTime()));
                }
            }
        }
        // 设置返回状态码
        page.setMaxLimit(0L);
        // 设置msg
        page.setCountId("success");
        page.setRecords(applyForms);
        page.setTotal(applyForms.size());
        return page;
    }

    @Override
    public String getNextApplyNumberByDeptNumber(String deptNumber) {
        List<Apply> applyList = applyMapper.selectList(new QueryWrapper<Apply>()
                .eq("dept_number", deptNumber)
                .orderByDesc("apply_number"));
        if (CollectionUtils.isNotEmpty(applyList)) {
            return String.valueOf(Integer.parseInt(applyList.get(0).getApplyNumber()) + 1);
        } else {
            return deptNumber + "0001";
        }
    }

    @Override
    @Transactional
    public boolean passApply(ApplyForm applyForm) throws RuntimeException {
        // 更新申请信息
        Apply apply = new Apply();
        apply.setApplyState(1);
        apply.setApproverName(applyForm.getApproverName());
        apply.setApprovalOpinion(applyForm.getApprovalOpinion());
        int updateApply = applyMapper.update(apply, new UpdateWrapper<Apply>()
                .eq("id", applyForm.getId()));
        // 更新用户信息
        User user = new User();
        // 获取意向部门编号
        Apply apply1 = applyMapper.selectOne(new QueryWrapper<Apply>()
                .eq("id", applyForm.getId()));
        user.setRoleType(applyForm.getApplyType());
        user.setDeptNumber(apply1.getDeptNumber());
        boolean updateUser = userService.update(user, new UpdateWrapper<User>()
                .eq("user_name", applyForm.getUserName()));

        return (updateUser && (updateApply >= 1));
    }

    @Override
    public boolean rejectApply(ApplyForm applyForm) {
        // 更新申请信息
        Apply apply = new Apply();
        apply.setApplyState(2);
        apply.setApproverName(applyForm.getApproverName());
        apply.setApprovalOpinion(applyForm.getApprovalOpinion());
        int updateApply = applyMapper.update(apply, new UpdateWrapper<Apply>()
                .eq("id", applyForm.getId()));

        return (updateApply >= 1);
    }

    @Override
    public boolean save(Apply entity) {
        int insert = applyMapper.insert(entity);
        return (insert >= 1);
    }

    @Override
    public boolean removeById(Serializable id) {
        int deleteById = applyMapper.deleteById(id);
        return (deleteById >= 1);
    }

    @Override
    public boolean update(Apply entity, Wrapper<Apply> updateWrapper) {
        int update = applyMapper.update(entity, updateWrapper);
        return (update >= 1);
    }

    @Override
    public int count(Wrapper<Apply> queryWrapper) {
        return applyMapper.selectCount(queryWrapper);
    }

    @Override
    public Apply getOne(Wrapper<Apply> queryWrapper) {
        return applyMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean saveBatch(Collection<Apply> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Apply> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Apply> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Apply entity) {
        return false;
    }

    @Override
    public Apply getOne(Wrapper<Apply> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Apply> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Apply> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Apply> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Apply> getEntityClass() {
        return null;
    }
}
