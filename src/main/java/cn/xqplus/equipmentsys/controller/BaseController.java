package cn.xqplus.equipmentsys.controller;

import cn.xqplus.equipmentsys.ext.CommonConst;
import cn.xqplus.equipmentsys.ext.JsonResult;
import cn.xqplus.equipmentsys.ext.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * API数据返回基类
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
            return CommonConst.SUCCESS;
        } else {
            return CommonConst.ERROR;
        }
    }

    protected <T> JsonResult<T> jr(){
        return jr("0", "");
    }

    protected <T> JsonResult<T> jr(String message){
        return jr("0", message);
    }

    protected <T> JsonResult<T> jr(T t){
        return jr("0", "", t);
    }

    protected  <T> JsonResult<T> jr(String code, String message){
        return new JsonResult<T>(code, message);
    }

    protected  <T> JsonResult<T> jr(String message, T t){
        return jr("0", message, t);
    }

    protected  <T> JsonResult<T> jr(String code, String message, T t){
        return new JsonResult<T>(code, message, t);
    }

    protected <T> PageResult<T> jr(IPage<T> page){
        return jr("0", "", page);
    }

    protected <T> PageResult<T> jr(String message, IPage<T> page){
        return jr("0", message, page);
    }

    protected <T> PageResult<T> jr(String code, String message, IPage<T> page){
        return new PageResult<T>(code, message, page);
    }
}
