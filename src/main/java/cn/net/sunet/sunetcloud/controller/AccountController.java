package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/19
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.Account;
import cn.net.sunet.sunetcloud.service.AccountServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping(value = "/acount")
@Api(value = "account",description = "账号管理")
public class AccountController {
    private final AccountServiceImpl accountService;
    private final JSONGenerator jsonGenerator;

    @Autowired
    public AccountController(AccountServiceImpl accountService, JSONGenerator jsonGenerator) {
        this.accountService = accountService;
        this.jsonGenerator = jsonGenerator;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String qurry() {
        List<HashMap> list = accountService.query();
        if (!list.isEmpty()) {
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("查询成功").setData(list).asJson();
        } else {
            return jsonGenerator.setStatus(Constant.OTHER_ERROR).setMsg("没有数据").asJson();
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.PUT)
    public String lock(@RequestParam String username) {
        int account = accountService.updateLock(username, (byte) 1);
        if (account != 1) {
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("锁定用户成功").asJson();
        } else {
            return jsonGenerator.setStatus(Constant.REQUEST_PARAMETER_ERROR).setMsg("不存在该用户").asJson();
        }
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.PUT)
    public String unlock(@RequestParam String username) {
        int account = accountService.updateLock(username, (byte) 0);
        if (account == 1) {
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("解锁用户成功").asJson();
        } else {
            return jsonGenerator.setStatus(Constant.REQUEST_PARAMETER_ERROR).setMsg("不存在该用户").asJson();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam String username,
                         @RequestParam String nickname,
                         @RequestParam int jobNumber,
                         @RequestParam String telephone,
                         @RequestParam String email,
                         @RequestParam String sex,
                         @RequestParam int departmentId,
                         @RequestParam int typeId) {
        Account account = new Account();
        account.setUsername(username);
        account.setAccountTypeId(typeId);
        account.setGender(sex);
        account.setDepartmentId(departmentId);
        account.setTelephone(telephone);
        account.setEmail(email);
        account.setEmployeeNumber(jobNumber);
        account.setNickname(nickname);
        int flag=accountService.update(account);
        if(flag==1){
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("修改用户成功").asJson();
        }
        else {
            return jsonGenerator.setStatus(Constant.REQUEST_ERROR).setMsg("修改失败").asJson();
        }
    }
}
