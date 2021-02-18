package cn.xqplus.equipmentsys.controller;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Json格式数据返回基类
 */

@ResponseBody
public abstract class BaseController {

    /**
     * String数据返回封装方法
     * @param flag boolean 操作结果标志位
     * @return String
     */
    public static String stringResult(boolean flag) {
        if (flag) {
            return "success";
        } else {
            return "error";
        }
    }
}
