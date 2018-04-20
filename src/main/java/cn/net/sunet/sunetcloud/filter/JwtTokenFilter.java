package cn.net.sunet.sunetcloud.filter;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/3/22
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn

*/

import cn.net.sunet.sunetcloud.config.GrantedAuthorityImpl;
import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.exception.UsernameIsExitedException;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import cn.net.sunet.sunetcloud.utils.Jedisutils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtTokenFilter extends GenericFilterBean {
/*public JwtTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }*/


    /**
     * 在此方法中检验客户端请求头中的token,
     * 如果存在并合法,就把token中的信息封装到 Authentication 类型的对象中,
     * 最后使用  SecurityContextHolder.getContext().setAuthentication(authentication); 改变或删除当前已经验证的 pricipal
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        String url = request.getRequestURI();
        logger.info(url);
        if (token == null || !token.startsWith("sunet")) {
            filterChain.doFilter(request, response);
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthencation(token, response);
            if (authenticationToken == null) {
                setErrorResponse(HttpStatus.BAD_REQUEST, (HttpServletResponse) servletResponse, Constant.TOKEN_AUTHENTICATE_FAIL,
                        "账号已注销登录");
            } else {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            }
        }
    }

    /**
     * 统一处理由过滤器引发的异常
     */

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, int code, String msg) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        // A class used for errors
        try {
            response.getWriter().write(new JSONGenerator().setStatus(code).setMsg(msg).asJson());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断token是否过期，包括用户是否退出登录和是否时间过期
     */

    private UsernamePasswordAuthenticationToken getAuthencation(String token, HttpServletResponse httpServletResponse) {
        String role = null;
        String username = null;
        Date date = null;
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("sunet")
                    .parseClaimsJws(token.replace("sunet", ""))
                    .getBody();
            username = claims.getAudience();
            date = claims.getExpiration();
            role = claims.getIssuer();
            String redistoken = Jedisutils.getInstance().getJedis().get(username);
            ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new GrantedAuthorityImpl(role));
            Date now = new Date();
            if (redistoken == null || !redistoken.equals(token.replace("sunet", ""))) {
                logger.error("账号已注销登录");

            }
            if (date.getTime() < now.getTime()) {
                throw new UsernameIsExitedException("该账号已过期请重新登录");
            }
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, roles);
            }
            return null;


        } catch (ExpiredJwtException e) {
            setErrorResponse(HttpStatus.BAD_REQUEST, httpServletResponse, Constant.TOKEN_AUTHENTICATE_FAIL, e.getMessage());
            return null;

        } catch (RuntimeException e) {
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse, 550, e.getMessage());
        } finally {
            return null;
        }

    }


}
