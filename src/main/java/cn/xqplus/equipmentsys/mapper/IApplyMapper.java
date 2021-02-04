package cn.xqplus.equipmentsys.mapper;

import cn.xqplus.equipmentsys.form.ApplyForm;
import cn.xqplus.equipmentsys.model.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 申请信息 数据层
 */

@Mapper
@Repository
public interface IApplyMapper extends BaseMapper<Apply> {

    /**
     * 获取申请 list
     * @param page 分页
     * @param wrapper 条件
     * @param name 当前用户
     * @return List<ApplyForm>
     */
    List<ApplyForm> getList(Page<ApplyForm> page, @Param("q") ApplyForm wrapper, String name);

}
