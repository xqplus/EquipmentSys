package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IRoleDeptMapper;
import cn.xqplus.equipmentsys.model.RoleDept;
import cn.xqplus.equipmentsys.service.IRoleDeptService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * 角色部门关联信息 服务层实现
 */

@Service
public class RoleDeptServiceImpl extends ServiceImpl<IRoleDeptMapper, RoleDept>
        implements IRoleDeptService {

    @Autowired
    private IRoleDeptMapper roleDeptMapper;

    //

}
