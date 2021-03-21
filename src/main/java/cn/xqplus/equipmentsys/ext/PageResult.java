package cn.xqplus.equipmentsys.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


/**
 * 返回数据封装类
 * @author chenq
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -5110055108252942454L;

    /**总页数*/
    @Getter
    @Setter
    private long total;

    /**数据*/
    @Getter
    @Setter
    private List<T> data;

    /**状态码*/
    @Getter
    @Setter
    private Object code;

    /**错误信息*/
    @Getter
    @Setter
    private String message;

    public PageResult(IPage<T> page){
        this(0, page);
    }

    public PageResult(Object code, IPage<T> page){
        this(code, "", page);
    }

    public PageResult(Object code, String message, IPage<T> page){
        this.total = page.getTotal();
        this.data = page.getRecords();
        this.code = code;
        this.message = message;
    }


}
