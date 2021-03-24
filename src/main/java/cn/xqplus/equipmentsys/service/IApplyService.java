package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.form.ApplyForm;
import cn.xqplus.equipmentsys.model.Apply;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 申请信息 服务层
 */

public interface IApplyService extends IService<Apply> {

    /**
     * 获取分页page
     * @param page 分页
     * @param wrapper 条件
     * @param userName 用户查询条件
     * @return Page<ApplyForm>
     */
    IPage<ApplyForm> selectPage(Page<ApplyForm> page, ApplyForm wrapper, String userName);

    /**
     * 获取最新的申请编号
     * @param deptNumber 部门编号
     * @return String 最新的申请编号
     */
    String getNextApplyNumberByDeptNumber(String deptNumber);

    /**
     * 申请通过
     * @param applyForm
     * @return
     */
    boolean passApply(ApplyForm applyForm);

    /**
     * 申请驳回
     * @param applyForm
     * @return
     */
    boolean rejectApply(ApplyForm applyForm);
}
