package cn.webdemo.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by 63042 on 2019/7/16.
 * 密码加密
 * SpringSecurity的类BCryptPasswordEncoder，可进行密码加密。输出长度为60。
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "admin";
        String pwd = BCryptPasswordEncoderUtils.encodePassword(password);
        System.out.println(pwd);
    }
}
