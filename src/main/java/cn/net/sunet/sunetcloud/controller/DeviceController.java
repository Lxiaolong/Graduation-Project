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
import cn.net.sunet.sunetcloud.domain.DevicePerformance;
import cn.net.sunet.sunetcloud.service.DevicePerformanceServiceImpl;
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
    @Autowired
    private DevicePerformanceServiceImpl devicePerformanceService;

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
    @RequestMapping(value = "/querysummary",method = RequestMethod.GET)
    public String querySummary(@RequestParam long deviceTypeId,
                               @RequestParam int page,
                               @RequestParam int count){
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent
                (deviceService.querySummary(deviceTypeId,page,count)).asJson();
    }
    @RequestMapping(value = "/configmaintenance",method = RequestMethod.POST)
    public String configMaintenance(@RequestParam long deviceId,
                                    @RequestParam long accountId){
        DevicePerformance devicePerformance =new DevicePerformance();
        devicePerformance.setDeviceId(deviceId);
        devicePerformance.setMalfuntionPersonId(accountId);
        DevicePerformance devicePerformance1=devicePerformanceService.selectByDeviceId(deviceId);
        try {

            if (devicePerformance1 == null) {
                devicePerformanceService.insert(devicePerformance);
            } else {
                devicePerformance.setId(devicePerformance1.getId());
                devicePerformanceService.update(devicePerformance);
            }
        }catch (Exception e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage())
                    .asJson();
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("请求成功").asJson();

    }
    @RequestMapping(value = "/queryStatus",method = RequestMethod.GET)
    public String queryStatus(){
        return jsonGenerator.createJSONGenerator().setContent(deviceService.queryStatus()).asJson();
    }

}
