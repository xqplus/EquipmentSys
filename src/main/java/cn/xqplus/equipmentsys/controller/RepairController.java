package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.RepairForm;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 维修信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/repair", name = "维修信息相关")
public class RepairController extends BaseController {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IRepairService repairService;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/page", name = "维修信息page")
    public Object page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, RepairForm wrapper){
        Page<RepairForm> pages = new Page<>(page, limit);
        return repairService.selectPage(pages, wrapper);
    }

    @GetMapping(value = "/repair", name = "设备维修")
    public String repair(@NotNull int id) {
        boolean repair = repairService.repair(id);
        return stringResult(repair);
    }

    @GetMapping(value = "/scrap", name = "设备报废")
    public String scrap(@NotNull int id) {
        return repairService.scrap(id);
    }

    @GetMapping(value = "/historyPage", name = "维修历史记录page")
    public Object historyPage(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int limit, RepairForm wrapper) {
        Page<RepairForm> historyPage = new Page<>(page, limit);
        return repairService.selectHistoryPage(historyPage, wrapper);
    }

    @PostMapping(value = "/historyDel", name = "维修历史记录删除")
    public String historyDel(@NotNull int id) {
        boolean removeById = repairService.removeById(id);
        return stringResult(removeById);
    }
}
