package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.model.Apply;
import cn.xqplus.equipmentsys.model.Repair;
import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.IApplyService;
import cn.xqplus.equipmentsys.service.IRepairService;
import cn.xqplus.equipmentsys.service.ITodoAndNoticeService;
import cn.xqplus.equipmentsys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 待办、通知、公告信息服务层实现
 */

@Service
public class TodoAndNoticeServiceImpl implements ITodoAndNoticeService {

    @Autowired
    private IApplyService applyService;

    @Autowired
    private IRepairService repairService;

    @Autowired
    private IUserService userService;

    @Override
    public List<Object> getTodoInfo() {
        List<Object> result = new ArrayList<>();
        // 获取当前登录用户信息
        User currentUserInfo = userService.getCurrentUserInfo();
        // 申请中职位数量
        int applyNum = applyService.count(new QueryWrapper<Apply>()
                .eq("apply_state", 0));
        // 当前为管理员才返回数量信息
        if (currentUserInfo.getRoleType() == 0) {
            result.add(applyNum);
        } else {
            result.add(0);
        }
        // 维修中设备数量
        int repairNum = repairService.count(new QueryWrapper<Repair>()
                .eq("repair_state", 1));
        // 当前为维修员才返回数量信息
        if (currentUserInfo.getRoleType() == 2) {
            result.add(repairNum);
        } else {
            result.add(0);
        }

        return result;
    }

    @Override
    public Integer getMyNotice() {
        User currentUserInfo = userService.getCurrentUserInfo();
        Apply apply = applyService.getOne(new QueryWrapper<Apply>()
                .eq("user_number", currentUserInfo.getUserNumber())
                .ne("apply_state", 0)
                .orderByDesc("create_time")
                .last("LIMIT 1"));
        if (null == apply) {
            return 0;
        }
        return apply.getApplyState();
    }

}
