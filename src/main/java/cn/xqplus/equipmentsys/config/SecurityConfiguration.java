package cn.xqplus.equipmentsys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * spring security 配置
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // UserDetailsService有多个实现类 @Qualifier指定
    @Qualifier("securityServiceImpl")
    @Autowired
    private UserDetailsService securityService;

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication()
        //        .passwordEncoder(new BCryptPasswordEncoder())
        //        .withUser("admin")
        //        .password(new BCryptPasswordEncoder().encode("123456"))
        //        .roles("admin");
        auth
                .userDetailsService(securityService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 各个页面的访问权限
                    .antMatchers("/", "/equipmentSys/router/index", "/index")
                        .permitAll()
                    .antMatchers("/equipmentSys/router/userManagement")
                        .hasRole("ADMIN")
                    .antMatchers("/equipmentSys/router/deptManagement")
                        .hasRole("ADMIN")
                    .antMatchers("/equipmentSys/router/equipManagement")
                        .hasAnyRole("ADMIN", "USER", "REPAIR")
                    .antMatchers("/equipmentSys/router/equipTypeManagement")
                        .hasRole("ADMIN")
                    .antMatchers("/equipmentSys/router/repairManagement")
                        .hasAnyRole("ADMIN", "REPAIR")
                    .antMatchers("/equipmentSys/router/repairHistory")
                        .hasAnyRole("ADMIN", "USER", "REPAIR")
                    .and()
                // 登录页定制
                .formLogin()
                    .loginPage("/equipmentSys/router/login")
                    .loginProcessingUrl("/equipmentSys/router/login")
                        .usernameParameter("userName").passwordParameter("password").permitAll()
                    .and()
                // 记住我功能定制 cookie保存14天
                .rememberMe().rememberMeParameter("switch")
                .and()
                // 退出后显示页面
                .logout().logoutSuccessUrl("/").permitAll();
        // 关闭防止csrf功能
        http.csrf().disable();
    }
}
