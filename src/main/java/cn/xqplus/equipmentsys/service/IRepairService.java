package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.form.RepairForm;
import cn.xqplus.equipmentsys.model.Repair;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 维修信息 服务层
 */

public interface IRepairService extends IService<Repair> {

    /**
     * 获取最新维修编号下一个，返回对象
     * @return Repair
     */
    Repair getNextRepairNumber();

    /**
     * 获取分页list
     * @param page 分页辅助
     * @param wrapper 查询条件
     * @return Page<RepairForm>
     */
    IPage<RepairForm> selectPage(Page<RepairForm> page, RepairForm wrapper);

    /**
     * 获取history分页list
     * @param page 分页辅助
     * @param wrapper 查询条件
     * @return Page<RepairForm>
     */
    IPage<RepairForm> selectHistoryPage(Page<RepairForm> page, RepairForm wrapper);

    /**
     * 设备维修
     * @param id 维修id
     * @return boolean
     */
    boolean repair(int id);

    /**
     * 设备报废
     * @param id 维修id
     * @return String
     */
    String scrap(int id);
}
