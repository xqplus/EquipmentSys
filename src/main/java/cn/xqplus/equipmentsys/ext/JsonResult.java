package cn.xqplus.equipmentsys.ext;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -469360326755607592L;

    /**
     * 状态码
     */
    @Getter
    @Setter
    private Object code;

    /**
     * 消息
     */
    @Getter
    @Setter
    private String message;

    /**
     * 返回数据
     */
    @Getter
    @Setter
    private T data;

    /**
     * 默认构造方法
     */
    public JsonResult() {
        this("0");
    }

    /**
     * 构造json格式
     * @param code	状态码
     */
    public JsonResult(Object code) {
        this(code, null);
    }

    /**
     * 构造json格式
     * @param code	数据状态
     * @param message	消息提示
     */
    public JsonResult(Object code, String message) {
        this(code, message, null);
    }

    /**
     * 构造json格式
     * @param code	数据状态
     * @param message	消息提示
     * @param data	视图层对象
     */
    public JsonResult(Object code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
