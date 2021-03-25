package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.RepairForm;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

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
    public PageResult<RepairForm> page(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int limit, RepairForm wrapper){
        IPage<RepairForm> iPage = repairService.selectPage(new Page<>(page, limit), wrapper);
        return jr(iPage);
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
    public PageResult<RepairForm> historyPage(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int limit, RepairForm wrapper) {
        IPage<RepairForm> iPage = repairService.selectHistoryPage(new Page<>(page, limit), wrapper);
        return jr(iPage);
    }

    @PostMapping(value = "/historyDel", name = "维修历史记录删除")
    public String historyDel(@NotNull int id) {
        boolean removeById = repairService.removeById(id);
        return stringResult(removeById);
    }

    @GetMapping(value = "/exportHistoryExcel", name = "维修历史信息 Excel 导出")
    public void exportHistoryExcel(@RequestParam(value = "ids") String[] ids, HttpServletResponse response) {
        repairService.exportExcel(Arrays.asList(ids), response);
    }
}
