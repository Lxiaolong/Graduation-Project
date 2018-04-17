package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/16
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.dao.AccountMapper;
import cn.net.sunet.sunetcloud.domain.Account;
import cn.net.sunet.sunetcloud.exception.DatabaseException;
import cn.net.sunet.sunetcloud.service.AdminOperation;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import cn.net.sunet.sunetcloud.utils.RequestData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Lxiaolong
 */
@RestController
public class LoginModuleController {
    @Autowired
    private AccountMapper accountMapper;


    @PostMapping("/register")
    public String doRegister(Account account) throws DatabaseException {
        // 此处省略校验逻辑
        if (new AdminOperation(accountMapper).insertAccount(account) != 0) {
            return new JSONGenerator().setCode(Constant.SUCCESS).setMsg("注册成功").asJson();
        } else {
            return new JSONGenerator().setCode(Constant.OTHER_ERROR).setMsg("注册失败").asJson();
        }
    }

    @GetMapping(value = "/index")
    @ApiOperation("测试")
    public String user() {

        return new JSONGenerator().setData("测试").asJson();
    }

    @RequestMapping(value = "/mobile_login", method = RequestMethod.POST)
    public String login(@RequestParam String phone, @RequestParam String code, HttpServletResponse httpServletResponse) throws
            IOException {
        String url = "https://webapi.sms.mob.com/sms/verify";
        String data = "appkey=123&phone=" + phone + "&zone=86&code=" + code;
        String result = RequestData.requestData(url, data);
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject.get("status"));
        if (jsonObject.get("status").equals(200)) {
            Account user = accountMapper.selectByUsername(phone);
            if (user != null) {
                String token = Jwts.builder()
                        .setAudience(user.getUsername())
                        //有效期为两个小时
                        .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000 * 60 * 2))
                        .setIssuer(user.getAccountType().getRoles())
                        //采用HS384加密方式，密钥为sunet
                        .signWith(SignatureAlgorithm.HS384, "sunet")
                        .compact();
                httpServletResponse.addHeader("token", token);
                return new JSONGenerator().setCode(Constant.SUCCESS).setMsg("登陆成功").asJson();
            }
            return new JSONGenerator().setCode(Constant.REQUEST_ERROR).asJson();
        }

        return new JSONGenerator().setCode(Constant.REQUEST_ERROR).asJson();
    }
}