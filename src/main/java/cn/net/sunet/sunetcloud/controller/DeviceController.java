package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/13
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.Device;
import cn.net.sunet.sunetcloud.service.DeviceServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping("/device")
@Api(value = "device",description = "设备基本信息管理")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceService;
    @Autowired
    private JSONGenerator jsonGenerator;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String inset(@ModelAttribute Device device) {
        String certificate=device.getNumber()+System.currentTimeMillis();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        certificate = bCryptPasswordEncoder.encode(certificate);
        device.setCertificate(certificate);
        try {
            deviceService.insert(device);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();
        }catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).asJson();
        }
    }
    @RequestMapping(value = "/querypage",method = RequestMethod.GET)
    public String queryPage(@RequestParam int page, @RequestParam int count){
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查找成功").setContent
                (deviceService.queryPage(page, count)).asJson();
    }
}
