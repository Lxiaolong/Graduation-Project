package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/16
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.Account;
import cn.net.sunet.sunetcloud.exception.DatabaseException;
import cn.net.sunet.sunetcloud.service.AccountServiceImpl;
import cn.net.sunet.sunetcloud.service.AdminOperation;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import cn.net.sunet.sunetcloud.utils.RequestData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Lxiaolong
 */
@RestController
@Api(value = "login", description = "登录注册管理")
public class LoginModuleController {

    @Autowired
    private  AccountServiceImpl accountMapper;
    @Autowired
    private JSONGenerator jsonGenerator;

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String nickname,
                             @RequestParam int jobNumber,
                             @RequestParam String telephone,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String sex,
                             @RequestParam int departmentId,
                             @RequestParam int typeId) throws DatabaseException {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setAccountTypeId(typeId);
        account.setGender(sex);
        account.setDepartmentId(departmentId);
        account.setTelephone(telephone);
        account.setEmail(email);
        account.setEmployeeNumber(jobNumber);
        account.setNickname(nickname);
        account.setIsLock((byte) 0);
        account.setIsCheck((byte) 1);
        // 此处省略校验逻辑
        try {
            new AdminOperation(accountMapper).insertAccount(account);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("注册成功").asJson();}
        catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.OTHER_ERROR).setMsg("注册失败").asJson();
        }
    }

    @GetMapping(value = "/index")
    @ApiOperation("测试")
    public String user() {

        return jsonGenerator.createJSONGenerator().setData("测试").asJson();
    }

    @RequestMapping(value = "/mobile_login", method = RequestMethod.POST)
    public String login(@RequestParam String phone, @RequestParam String code, HttpServletResponse httpServletResponse) throws
            IOException {
        String url = "https://webapi.sms.mob.com/sms/verify";
        String data = "appkey=24e34fb545aba&phone=" + phone + "&zone=86&code=" + code;
        String result = RequestData.requestData(url, data);
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject.get("status"));
        if (jsonObject.get("status").equals(200)) {
            Account user = accountMapper.selectByPhone(phone);
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
                return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("登陆成功").asJson();
            }
            return jsonGenerator.createJSONGenerator().setStatus((Integer) jsonObject.get("status")).asJson();
        }

        return jsonGenerator.createJSONGenerator().setStatus((Integer) jsonObject.get("status")).asJson();
    }
}