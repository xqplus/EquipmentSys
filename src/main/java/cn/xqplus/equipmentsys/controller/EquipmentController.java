package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/equipment", name = "设备信息相关")
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IRepairService repairService;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/page", name = "设备信息page")
    public Object page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, EquipmentForm wrapper){
        Page<EquipmentForm> pages = new Page<>(page, limit);
        return equipmentService.selectPage(pages, wrapper);
    }

    @GetMapping(value = "/getNextEquipNumber", name = "获取最新设备编号下一个")
    public Object getNextEquipNumber() {
        // 返回的是Equipment对象
        return equipmentService.getNextEquipNumber();
    }

    @PostMapping(value = "/add", name = "新增")
    public String add(Equipment equipment) {
        // 保存设备信息
        boolean save = equipmentService.save(equipment);
        if (save) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/update", name = "设备信息变更")
    public String update(Equipment equipment) {
        // 根据设备编号变更
        boolean update = equipmentService.update(equipment, new UpdateWrapper<Equipment>()
                .eq("equip_number", equipment.getEquipNumber()));
        if (update) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/delete", name = "设备信息删除")
    public String delete(@NotNull int id) {
        // 根据主键id删除
        boolean b = equipmentService.removeById(id);
        if (b) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/reportRepair", name = "设备报修")
    public String reportRepair(@NotNull int id) {
        Equipment equipment = equipmentService.getById(id);
        // 只有使用中的设备才能报修
        if (equipment.getEquipState() == 0) {
            Equipment equipment1 = new Equipment();
            equipment1.setEquipState(1);
            // 改变设备状态
            boolean id1 = equipmentService.update(equipment1, new UpdateWrapper<Equipment>()
                    .eq("id", id));
            // 创建维修信息
            Repair repair = repairService.getNextRepairNumber();
            repair.setEquipNumber(equipment.getEquipNumber());
            // 获得当前登录用户
            User currentUserInfo = userService.getCurrentUserInfo();
            repair.setReporterNumber(currentUserInfo.getUserNumber());
            boolean save = repairService.save(repair);
            if (id1 && save) {
                return "success";
            } else {
                return "error";
            }
        } else {
            // 流程不正确 （使用中 -报修-> 维修中 -报废-> 已报废）
            return "noProcess";
        }
    }

    @GetMapping(value = "/scrap", name = "设备报废")
    public String scrap(@NotNull int id) {
        Equipment equipment = equipmentService.getById(id);
        // 只有维修中的设备才能报废
        if (equipment.getEquipState() == 1) {
            Equipment equipment1 = new Equipment();
            equipment1.setEquipState(2);
            boolean id1 = equipmentService.update(equipment1, new UpdateWrapper<Equipment>()
                    .eq("id", id));
            if (id1) {
                return "success";
            } else {
                return "error";
            }
        } else {
            // 流程不正确 （使用中 -报修-> 维修中 -报废-> 已报废）
            return "noProcess";
        }
    }
}
