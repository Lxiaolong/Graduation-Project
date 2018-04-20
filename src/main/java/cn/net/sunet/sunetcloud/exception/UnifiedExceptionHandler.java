package cn.net.sunet.sunetcloud.exception;/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/3/27
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice

public class UnifiedExceptionHandler {
    @ExceptionHandler(value = OtherException.class)
    public Map otherException(HttpServletRequest httpServletRequest, OtherException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("url", httpServletRequest.getRequestURL());
        return new JSONGenerator().setStatus(Constant.OTHER_ERROR).setMsg(e.getMessage()).setData(data).asJsonObject();

    }
    @ExceptionHandler(value = DatabaseException.class)
    public Map databaseException(HttpServletRequest httpServletRequest, DatabaseException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("url", httpServletRequest.getRequestURL());
        return new JSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setData(data).asJsonObject();

    }
    @ExceptionHandler(value = RequestParameterException.class)
    public Map serverException(HttpServletRequest httpServletRequest, RequestParameterException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("url", httpServletRequest.getRequestURL());
        return new JSONGenerator().setStatus(Constant.REQUEST_PARAMETER_ERROR).setMsg(e.getMessage()).setData(data)
                .asJsonObject();

    }
    @ExceptionHandler(value = ExpiredJwtException.class)
    public Map expiredJwtException(HttpServletRequest httpServletRequest, ExpiredJwtException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("url", httpServletRequest.getRequestURL());
        return new JSONGenerator().setStatus(Constant.REQUEST_PARAMETER_ERROR).setMsg(e.getMessage()).setData(data)
                .asJsonObject();

    }
/*    @ExceptionHandler(value = RuntimeException.class)
    public Map myException(RuntimeException e) {
        Map<String, Object> data = new HashMap<>();
        //data.put("url", httpServletRequest.getRequestURL());
        return new JSONGenerator().setCode(Constant.LOGIC_ERROR).setMsg(e.getMessage()).setData(data).asJsonObject();

    }*/
    /*@ExceptionHandler(value = Exception.class)
    public Map myException(HttpServletRequest httpServletRequest,Exception e) {

        Map<String, Object> data = new HashMap<>();
        data.put("url", httpServletRequest.getRequestURL());

        return new JSONGenerator().setCode(Constant.LOGIC_ERROR).setMsg(e.getMessage()).setData(data).asJsonObject();

    }*/
}
