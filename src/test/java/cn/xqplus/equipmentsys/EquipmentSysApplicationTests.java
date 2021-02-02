package cn.xqplus.equipmentsys;

import cn.xqplus.equipmentsys.model.User;
import cn.xqplus.equipmentsys.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@SpringBootTest
class EquipmentSysApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
        // 当前时间戳
        System.out.println(new Date().getTime());
    }

    @Test
    void currentUserTest() {
        // 当前登录用户
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUser);
    }

    @Test
    void redisTemplateTest() {
        // redis 存取
        User user = new User();
        user.setUserName("zhangsan");
        redisUtils.set("user", user);
        System.out.println(redisUtils.get("user"));
        //redisUtils.del();
    }
}
