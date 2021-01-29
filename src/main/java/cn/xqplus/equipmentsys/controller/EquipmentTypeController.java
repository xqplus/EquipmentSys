package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.model.EquipmentType;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IEquipmentTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备类型信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/equipmentType", name = "设备类型信息相关")
public class EquipmentTypeController {

    @Autowired
    private IEquipmentTypeService equipmentTypeService;

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping(value = "/page", name = "设备类型信息page")
    public Object page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int limit, EquipmentTypeForm wrapper){
        Page<EquipmentTypeForm> pages = new Page<>(page, limit);
        return equipmentTypeService.selectPage(pages, wrapper);
    }

    @GetMapping(value = "/getNextEquipTypeNumber", name = "获取最新设备类型编号下一个")
    public Object getNextEquipNumber() {
        // 返回的是EquipmentType对象
        return equipmentTypeService.getNextEquipTypeNumber();
    }

    @PostMapping(value = "/add", name = "设备类型信息新增")
    public String add(EquipmentType equipmentType) {
        // 保存设备类型信息
        boolean save = equipmentTypeService.save(equipmentType);
        if (save) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/update", name = "设备类型信息变更")
    public String update(EquipmentType equipmentType) {
        Equipment equipment = new Equipment();
        equipment.setEquipTypeNumber(equipmentType.getEquipTypeNumber());
        Equipment equipment1 = equipmentService.getOne(new QueryWrapper<>(equipment)
                .last("LIMIT 1"));
        if (null == equipment1) {
            // 根据设备类型编号变更
            boolean i = equipmentTypeService.update(equipmentType, new UpdateWrapper<EquipmentType>()
                    .eq("equip_type_number", equipmentType.getEquipTypeNumber()));
            if (i) {
                return "success";
            } else {
                return "error";
            }
        } else {
            // 类型下存在设备
            return "existsEquip";
        }
    }

    @PostMapping(value = "/delete", name = "设备类型信息删除")
    public String delete(@NotNull int id) {
        // 判断类型下是否有设备
        EquipmentType equipmentType = equipmentTypeService.getById(id);
        Equipment equipment = new Equipment();
        equipment.setEquipTypeNumber(equipmentType.getEquipTypeNumber());
        Equipment one = equipmentService.getOne(new QueryWrapper<>(equipment)
                .last("LIMIT 1"));
        if (null == one) {
            boolean i = equipmentTypeService.removeById(id);
            if (i) {
                return "success";
            } else {
                return "error";
            }
        } else {
            // 类型下存在设备
            return "existsEquip";
        }
    }
}
