package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.PageResult;
import cn.xqplus.equipmentsys.form.EquipmentForm;
import cn.xqplus.equipmentsys.model.Equipment;
import cn.xqplus.equipmentsys.service.IEquipmentService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 设备信息管理 接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/equipment", name = "设备信息相关")
public class EquipmentController extends BaseController {

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping(value = "/page", name = "设备信息page")
    public PageResult<EquipmentForm> page(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int limit, EquipmentForm wrapper){
        IPage<EquipmentForm> iPage = equipmentService.selectPage(new Page<>(page, limit), wrapper);
        return jr(iPage);
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
        return stringResult(save);
    }

    @PostMapping(value = "/update", name = "设备信息变更")
    public String update(Equipment equipment) {
        // 根据设备编号变更
        boolean update = equipmentService.update(equipment, new UpdateWrapper<Equipment>()
                .eq("equip_number", equipment.getEquipNumber()));
        return stringResult(update);
    }

    @PostMapping(value = "/delete", name = "设备信息删除")
    public String delete(@NotNull int id) {
        // 根据主键id删除
        boolean removeById = equipmentService.removeById(id);
        return stringResult(removeById);
    }

    @GetMapping(value = "/reportRepair", name = "设备报修")
    public String reportRepair(@NotNull int id, String faultRemark) {
        return equipmentService.reportRepair(id, faultRemark);
    }

    @PostMapping(value = "deleteBatch", name = "批量删除")
    public String deleteBatch(@NotNull @RequestParam(value = "ids[]") String[] ids) {
        // 批量删除设备信息 前提是该批量设备是已报废状态
        List<Equipment> equipmentList = equipmentService.listByIds(Arrays.asList(ids));
        for (Equipment e : equipmentList) {
            if (e.getEquipState() != 2) {
                return stringResult(false);
            }
        }
        // 根据 ids 批量删除
        boolean removeByIds = equipmentService.removeByIds(Arrays.asList(ids));
        return stringResult(removeByIds);
    }

    @GetMapping(value = "exportExcel", name = "Excel 导出")
    public void exportExcel(@RequestParam(value = "ids") String[] ids, HttpServletResponse response) {
        equipmentService.exportExcel(Arrays.asList(ids), response);
    }

}
