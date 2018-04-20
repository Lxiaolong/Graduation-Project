package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/17
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.AccountType;
import cn.net.sunet.sunetcloud.domain.DeviceType;
import cn.net.sunet.sunetcloud.service.AccountTypeServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceTypeServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping(value = "/devicetype")
@Api(value = "devicetype", description = "设备种类管理")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeServiceImpl deviceTypeService;
    @Autowired
    private AccountTypeServiceImpl accountTypeService;
    @Autowired
    private JSONGenerator jsonGenerator;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        List<DeviceType> typeList = deviceTypeService.query();
        List<AccountType> accountTypes = accountTypeService.query();
        HashMap hashMap = new HashMap();
        hashMap.put("rank", accountTypes);
        hashMap.put("department", typeList);
        if (!hashMap.isEmpty()) {
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("查询成功").setData(hashMap).asJson();
        } else {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg("无查询结果").asJson();
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute DeviceType deviceType) {
        if (deviceTypeService.insert(deviceType)) {
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("添加部门成功").asJson();
        } else {
            return jsonGenerator.setStatus(Constant.OTHER_ERROR).setMsg("添加部门失败").asJson();
        }
    }

}
