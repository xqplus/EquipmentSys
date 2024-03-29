package cn.xqplus.equipmentsys.service.impl;

import cn.xqplus.equipmentsys.mapper.IRoleMapper;
import cn.xqplus.equipmentsys.mapper.IUserMapper;
import cn.xqplus.equipmentsys.model.Role;
import cn.xqplus.equipmentsys.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security 安全认证服务层
 * implements security UserDetailsService
 */

@Service
public class SecurityServiceImpl implements UserDetailsService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IRoleMapper roleMapper;

    private final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("user_name", userName));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 用户存在
        // 创建用户role集合 暂支持一种角色，待优化
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 根据用户信息查询角色信息
        List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>()
                .eq("role_type", user.getRoleType()));
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleAuth()));
            }
        }
        logger.debug(() -> userName + " 登入");

        // 这里的User是spring security的用户认证类
        return new org.springframework.security.core.userdetails
            .User(user.getUserName(), user.getPassword(), authorities);
        }
}
