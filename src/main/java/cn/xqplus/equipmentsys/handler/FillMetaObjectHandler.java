package cn.xqplus.equipmentsys.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    /**
     * 数据新增填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date().getTime(), metaObject);
        this.setFieldValByName("updateTime", new Date().getTime(), metaObject);
    }

    /**
     * 数据更新填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date().getTime(), metaObject);
    }
}
