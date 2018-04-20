package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.Device;
import cn.net.sunet.sunetcloud.domain.DeviceQuality;
import cn.net.sunet.sunetcloud.domain.DeviceRuntime;
import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;
import cn.net.sunet.sunetcloud.exception.DatabaseException;
import cn.net.sunet.sunetcloud.service.DeviceQualityServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceRuntimeServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceServiceImpl;
import cn.net.sunet.sunetcloud.service.MaintainMalfunctionServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping("/collection")
@Api(value = "collection", description = "数据采集接口")
@EnableAsync
public class Collection {
    @Autowired
    private DeviceQualityServiceImpl deviceQualityService;
    @Autowired
    private DeviceServiceImpl deviceService;
    @Autowired
    private DeviceRuntimeServiceImpl deviceRuntimeService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @Autowired
    private MaintainMalfunctionServiceImpl maintainMalfunctionService;

    @RequestMapping(value = "/devicequality", method = RequestMethod.POST)
    public String devicequality(@ModelAttribute DeviceQuality deviceQuality, @RequestParam String certificate) {
        long id = deviceQuality.getDeviceId();
        Device device = deviceService.selectById(id);
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(format.format(time));
            deviceQuality.setCollectionTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (device != null) {
            if (certificate.equals(device.getCertificate())) {
                deviceQualityService.insert(deviceQuality);
                return jsonGenerator.setStatus(Constant.SUCCESS).asJson();
            } else {
                return jsonGenerator.setStatus(Constant.LOGIC_ERROR).asJson();
            }
        }
        return jsonGenerator.setStatus(Constant.REQUEST_PARAMETER_ERROR).asJson();
    }

    @RequestMapping(value = "/deviceruntime/test_time", method = RequestMethod.POST)
    public String testTime(@ModelAttribute DeviceRuntime deviceRuntime) throws DatabaseException {
        try {
            deviceRuntime.setAdditiveOutput(deviceRuntimeService.selectTestTime(deviceRuntime.getDeviceId()).getAdditiveOutput());
            deviceRuntimeService.insert(deviceRuntime);
            return "ok";
        } catch (DataAccessException e) {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setData(e).asJson();
        }

    }

    @RequestMapping(value = "/deviceruntime/work_time", method = RequestMethod.POST)
    public String workTime(@ModelAttribute DeviceRuntime deviceRuntime) throws DatabaseException {
        try {
            deviceRuntime.setId(deviceRuntimeService.selectTestTime(deviceRuntime.getDeviceId()).getId());
            deviceRuntimeService.update(deviceRuntime);
            return "ok";
        } catch (DataAccessException e) {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setData(e).asJson();
        }

    }

    @RequestMapping(value = "/deviceruntime/down_time", method = RequestMethod.POST)
    public String downtime(@ModelAttribute DeviceRuntime deviceRuntime) throws DatabaseException {
        try {
            DeviceRuntime deviceRuntime1=deviceRuntimeService.selectTestTime(deviceRuntime.getDeviceId());
            deviceRuntime.setId(deviceRuntime1.getId());
            deviceRuntime.setAdditiveOutput(deviceRuntime1.getAdditiveOutput()+deviceRuntime.getRuntimeOutput());
            deviceRuntimeService.update(deviceRuntime);
            return "ok";
        } catch (DataAccessException e) {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setData(e).asJson();
        }

    }
    @RequestMapping(value = "/devicemaintain",method = RequestMethod.POST)
    public String maintain(@ModelAttribute MaintainMalfunction maintainMalfunction){
        try {
            maintainMalfunctionService.insert(maintainMalfunction);
            return "ok";
        }catch (DataAccessException e){
            return "notok";
        }
    }


}
