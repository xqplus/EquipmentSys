package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.RepairForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 维修信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/repair", name = "维修信息相关")
public class RepairController {

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
        Repair repair = repairService.getById(id);
        Equipment equipment = new Equipment();
        equipment.setEquipState(0);
        // 更新设备状态
        boolean equipUpdate = equipmentService.update(equipment, new UpdateWrapper<Equipment>()
                .eq("equip_number", repair.getEquipNumber()));
        Repair repair1 = new Repair();
        repair1.setRepairerNumber(userService.getCurrentUserInfo().getUserNumber());
        // 维修完成，维修状态
        repair1.setRepairState(0);
        // 更新维修信息
        boolean repairUpdate = repairService.update(repair1, new UpdateWrapper<Repair>()
                .eq("id", id));
        if (equipUpdate && repairUpdate) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/scrap", name = "设备报废")
    public String scrap(@NotNull int id) {
        Repair repair = repairService.getById(id);
        Equipment equipment = equipmentService.getOne(new QueryWrapper<Equipment>()
                .eq("equip_number", repair.getEquipNumber()));
        // 只有维修中的设备才能报废
        if (equipment.getEquipState() == 1) {
            Equipment equipment1 = new Equipment();
            equipment1.setEquipState(2);
            boolean update = equipmentService.update(equipment1, new UpdateWrapper<Equipment>()
                    .eq("equip_number", equipment.getEquipNumber()));
            Repair repair1 = new Repair();
            repair1.setRepairerNumber(userService.getCurrentUserInfo().getUserNumber());
            // 报废处理
            repair1.setRepairState(2);
            boolean repairUpdate = repairService.update(repair1, new UpdateWrapper<Repair>()
                    .eq("id", id));
            if (update && repairUpdate) {
                return "success";
            } else {
                return "error";
            }
        } else {
            // 流程不正确 （使用中 -报修-> 维修中 -报废-> 已报废）
            return "noProcess";
        }
    }

    @GetMapping(value = "/historyPage", name = "维修历史记录page")
    public Object historyPage(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int limit, RepairForm wrapper) {
        Page<RepairForm> historyPage = new Page<>(page, limit);
        return repairService.selectHistoryPage(historyPage, wrapper);
    }
}
