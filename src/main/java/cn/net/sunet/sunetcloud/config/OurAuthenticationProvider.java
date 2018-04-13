package cn.net.sunet.sunetcloud.config;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/3/22
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn

*/


import cn.net.sunet.sunetcloud.exception.UsernameIsExitedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class OurAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public OurAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder
            bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取认证的用户名 & 密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();


        //通过用户名从数据库中查询该用户
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);


        //判断密码(采用BCRypt加密)是否正确
        String dbPassword = userDetails.getPassword();
        System.out.println(dbPassword);
        Boolean flag = bCryptPasswordEncoder.matches(password, dbPassword);
        System.out.println(password);
        if (!flag) {
            throw new UsernameIsExitedException("密码错误");
        }
        // 还可以从数据库中查出该用户所拥有的权限,设置到 authorities 中去,这里模拟数据库查询.

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

        return auth;


    }
}
