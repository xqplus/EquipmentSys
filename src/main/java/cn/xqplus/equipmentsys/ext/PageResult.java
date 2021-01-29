package cn.xqplus.equipmentsys.ext;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;


/**
 * 返回数据封装类,待优化
 * @param <T>
 */
@Deprecated
public class PageResult<T> extends Page<T> implements IPage<T> {


    /**
     * layui状态码code
     */
    @Getter
    @Setter
    private long code = 0;

    /**
     * layui返回信息msg
     */
    @Getter
    @Setter
    private String msg = "";

    /**
     * 分页查询 limit 参数
     */
    @Getter
    @Setter
    private long limitStart;

    /**
     * 构造函数
     * @param current
     * @param size
     * @param code layui回调状态码
     * @param msg layui回调信息
     */
    public PageResult(long current, long size, long code, String msg) {
        super(current, size);
        this.code = code;
        this.msg = msg;
    }


}
