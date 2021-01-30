package cn.xqplus.equipmentsys;

import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.Date;

public class test {
    @Test
    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println(new Date().getTime());
//        System.out.println("ja1va".indexOf("1"));
//        int i = "ja1va".indexOf("1");
//        System.out.println("java".substring(2,3));
        String str = "10";

//        System.out.println(String.format("%03d%s",0, str));
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUser);

    }
}
