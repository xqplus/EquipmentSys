package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.model.Repair;
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
}
