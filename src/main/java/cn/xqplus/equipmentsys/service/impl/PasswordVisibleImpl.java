package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IPasswordVisibleMapper;
import cn.xqplus.equipmentsys.model.PasswordVisible;
import cn.xqplus.equipmentsys.service.IPasswordVisibleService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 可见密码信息 服务层实现
 */

@Service
public class PasswordVisibleImpl extends ServiceImpl<IPasswordVisibleMapper, PasswordVisible>
        implements IPasswordVisibleService {

    @Autowired
    private IPasswordVisibleMapper passwordVisibleMapper;

    //

}
