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
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Lxiaolong
 */

@RestController
@RequestMapping(value = "/acount")
@Api(value = "account", description = "账号管理")
public class AccountController {
    private final AccountServiceImpl accountService;
    private final JSONGenerator jsonGenerator;

    @Autowired
    public AccountController(AccountServiceImpl accountService, JSONGenerator jsonGenerator) {
        this.accountService = accountService;
        this.jsonGenerator = jsonGenerator;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(@RequestParam int page,
                        @RequestParam int count) {
        try {
            HashMap hashMap = accountService.queryPage(page, count);

            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent(hashMap)
                    .asJson();
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.OTHER_ERROR).setMsg("没有数据").asJson();
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.PUT)
    public String lock(@RequestParam String username) {

        try {
            accountService.updateLock(username, (byte) 1);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("锁定用户成功").asJson();

        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.REQUEST_PARAMETER_ERROR).setMsg("不存在该用户")
                    .asJson();
        }
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.PUT)
    public String unlock(@RequestParam String username) {
        try {
            accountService.updateLock(username, (byte) 0);
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("解锁用户成功").asJson();
        } catch (DataAccessException e) {
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
        try {
            accountService.update(account);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("修改用户成功").asJson();
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.REQUEST_ERROR).setMsg("修改失败").asJson();
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam String username,
                         @RequestParam(required = false,defaultValue = "1") int page,
                         @RequestParam(required = false,defaultValue = "10") int count){
        try{
            HashMap hashMap=accountService.delete(page, count, username);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").setContent(hashMap)
                    .asJson();
        }catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg
                    ("请把此员工负责或维修的设备转接后删除").asJson();
        }
    }
    @RequestMapping(value = "/querymaintenance",method = RequestMethod.GET)
    public String queryMaintenance(){
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查找成功").setContent(accountService
                .queryMaintenance()).asJson();
    }
}
