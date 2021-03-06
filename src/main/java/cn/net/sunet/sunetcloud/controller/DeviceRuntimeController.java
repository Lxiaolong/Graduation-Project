package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/2
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.DeviceParePartsManage;
import cn.net.sunet.sunetcloud.domain.DevicePerformance;
import cn.net.sunet.sunetcloud.domain.DeviceQuality;
import cn.net.sunet.sunetcloud.domain.DeviceRuntime;
import cn.net.sunet.sunetcloud.service.DevicePerformanceServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceQualityServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceRuntimeServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping(value = "/deviceruntime")
@Api(value = "deviceruntime", description = "设备报表")
public class DeviceRuntimeController {

    @Autowired
    private DeviceRuntimeServiceImpl deviceRuntimeService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @Autowired
    private DeviceQualityServiceImpl deviceQualityService;
    @Autowired
    private DevicePerformanceServiceImpl devicePerformanceService;

    @RequestMapping(value = "/oee/queryByTime", method = RequestMethod.GET)
    public String queryByTime(Principal principal, @RequestParam long deviceId,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
        try{
            List<DeviceRuntime> deviceRuntimes=deviceRuntimeService.queryByTime(deviceId,startTime,endTime);
            List<DeviceQuality> deviceQualities=deviceQualityService.queryByTime(deviceId, startTime, endTime);
            System.out.println("pr"+principal.getName());
            if(deviceRuntimes.isEmpty()){
                return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询为空").asJson();
            }
            List<?> result=deviceRuntimeService.timeActivation(deviceRuntimes,deviceQualities);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setContent(result).asJson();
        }
        catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("查询出错").asJson();
        }

    }
    @RequestMapping(value = "/quality/queryByTime",method = RequestMethod.GET)
    public String quality(@RequestParam long deviceId,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime){
        return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("查询成功").setContent(deviceQualityService.queryByTime
                (deviceId, startTime, endTime)).asJson();
    }
    @RequestMapping(value ="/stability",method = RequestMethod.GET)
    public String stability(@RequestParam long deviceId){
        DevicePerformance devicePerformance=devicePerformanceService.queryBydeviceId(deviceId);
        HashMap hashMap=new HashMap();
        hashMap.put("MTTR",devicePerformance.getMttrTime());
        hashMap.put("MTBF",devicePerformance.getMtbfTime());
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent(hashMap)
                .asJson();
    }
}
