package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.model.Equipment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 设备信息 服务层
 */

public interface IEquipmentService extends IService<Equipment>, ICommonService {

    /**
     * @param page 分页辅助
     * @param wrapper 搜索查询条件
     * @return Page<EquipmentForm>
     */
    IPage<EquipmentForm> selectPage(Page<EquipmentForm> page, EquipmentForm wrapper);

    /**
     * 获取最新的设备编号下一个，用于新增
     * @return equipNumber
     */
    Equipment getNextEquipNumber();

    /**
     * 设备报修
     * @param id 设备id
     * @param faultRemark 故障描述
     * @return 回调值
     */
    String reportRepair(int id, String faultRemark);

}
