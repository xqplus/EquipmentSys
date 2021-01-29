package cn.xqplus.equipmentsys.query;

import cn.xqplus.equipmentsys.model.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;

@Deprecated
public class QUserWrapper extends Wrapper<User> {
    @Override
    public User getEntity() {
        return null;
    }

    @Override
    public MergeSegments getExpression() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getSqlSegment() {
        return null;
    }

}
