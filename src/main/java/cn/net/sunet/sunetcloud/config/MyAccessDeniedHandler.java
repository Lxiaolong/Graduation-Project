package cn.net.sunet.sunetcloud.config;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/26
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lxiaolong
 */
@Component
public class MyAccessDeniedHandler  implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(Constant.AUTHORITY);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(new JSONGenerator().createJSONGenerator().setStatus(Constant
                .AUTHORITY).setMsg("该用户权限不足").asJson());
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
