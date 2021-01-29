package cn.xqplus.equipmentsys.service;

import cn.xqplus.equipmentsys.model.PasswordVisible;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 可见密码信息 服务层
 */

public interface IPasswordVisibleService extends IService<PasswordVisible> {

    /**
     * 修改可见密码
     * @param passwordVisible
     * @return boolean
     */
    boolean updatePasswordVisible(PasswordVisible passwordVisible, UpdateWrapper<PasswordVisible> wrapper);

}
