package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.EquipmentTypeForm;
import cn.xqplus.equipmentsys.model.EquipmentType;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import cn.xqplus.equipmentsys.service.IEquipmentTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 设备类型信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/equipmentType", name = "设备类型信息相关")
public class EquipmentTypeController extends BaseController {

    @Autowired
    private IEquipmentTypeService equipmentTypeService;

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping(value = "/page", name = "设备类型信息page")
    public PageResult<EquipmentTypeForm> page(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int limit, EquipmentTypeForm wrapper){
        IPage<EquipmentTypeForm> iPage = equipmentTypeService.selectPage(new Page<>(page, limit), wrapper);
        return jr(iPage);
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
        return stringResult(save);
    }

    @PostMapping(value = "/update", name = "设备类型信息变更")
    public String update(EquipmentType equipmentType) {
        return equipmentTypeService.updateEquipType(equipmentType);
    }

    @PostMapping(value = "/delete", name = "设备类型信息删除")
    public String delete(@NotNull int id) {
        return equipmentTypeService.deleteEquipTypeById(id);
    }
}
