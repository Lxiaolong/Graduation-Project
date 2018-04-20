package cn.net.sunet.sunetcloud.filter;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/3/22
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn

*/



import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import cn.net.sunet.sunetcloud.utils.Jedisutils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

/* public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, "POST"));
        this.authenticationManager = authenticationManager;

    }
    */

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

/**
     * 接收并解析用户登陆信息  /login,
     * 为已验证的用户返回一个已填充的身份验证令牌，表示成功的身份验证
     * 返回null，表明身份验证过程仍在进行中。在返回之前，实现应该执行完成该过程所需的任何额外工作。
     * 如果身份验证过程失败，就抛出一个AuthenticationException
     *
     * @param httpServletRequest  从中提取参数并执行身份验证
     * @param httpServletResponse 如果实现必须作为多级身份验证过程的一部分(比如OpenID)进行重定向，则可能需要响应
     * @return 身份验证的用户令牌，如果身份验证不完整，则为null。
     * @throws AuthenticationException
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        username = username.trim();
        ArrayList<GrantedAuthority> authoritise = new ArrayList<>();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password, authoritise);
        //利用authenticate接收一个token参数，返回一个完全的经过身份验证证的对象
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        return authenticate;

    }

/**
     * 登陆成功后,此方法会被调用,因此我们可以在次方法中生成token,并返回给客户端
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setAudience(authResult.getName())
                //有效期为两个小时
                .setExpiration(new Date(System.currentTimeMillis() + 60  * 1000*60*2))
                .setIssuer(authResult.getAuthorities().toArray(new GrantedAuthority[authResult.getAuthorities().size
                        ()])[0].getAuthority())
                //采用HS384加密方式，密钥为sunet
                .signWith(SignatureAlgorithm.HS384, "sunet")
                .compact();
        //将生成的token存入redis，用于以后的验证使用

        Jedisutils.getInstance().getJedis().set(authResult.getName(), token);
        response.addHeader("token", "sunet" + token);
        response.setStatus(200);
        try {
            PrintWriter out = response.getWriter();
            out.write(new JSONGenerator().setStatus(Constant.SUCCESS).setMsg("ok").setData(authResult.getAuthorities()
                    .iterator())
                    .asJson
                    ());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
