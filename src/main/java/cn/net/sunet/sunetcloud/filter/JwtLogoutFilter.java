package cn.net.sunet.sunetcloud.filter;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/3/26
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */


import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import cn.net.sunet.sunetcloud.utils.Jedisutils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtLogoutFilter implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String token=httpServletRequest.getHeader("token");
        Claims claims = Jwts.parser().setSigningKey("sunet")
                .parseClaimsJws(token.replace("sunet", ""))
                .getBody();
        String username = claims.getAudience();
        Jedisutils.getInstance().getJedis().del(username);
        httpServletResponse.setStatus(200);
        try{
            PrintWriter out=httpServletResponse.getWriter();
            //HashMap jsonGenerator = (HashMap) new JSONGenerator().setCode(200).setMsg("注销成功").getJson();
            out.write(new JSONGenerator().setCode(200).setMsg("注销成功").asJson());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
