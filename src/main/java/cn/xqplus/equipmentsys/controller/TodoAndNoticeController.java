package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.service.ITodoAndNoticeService;
import cn.xqplus.equipmentsys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 待办、通知、公告信息接口
 */

@RestController
@RequestMapping(value = "/equipmentSys/todoAndNotice", name = "待办、公告信息")
public class TodoAndNoticeController {

    @Autowired
    private ITodoAndNoticeService todoAndNoticeService;

    @GetMapping(value = "/todo", name = "待办")
    public Object todo() {
        return todoAndNoticeService.getTodoInfo();
    }

    @GetMapping(value = "/myNotice", name = "我的通知，待阅")
    public Object myNotice() {
        return todoAndNoticeService.getMyNotice();
    }

}
