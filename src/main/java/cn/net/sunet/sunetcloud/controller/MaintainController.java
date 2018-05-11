package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/11
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.DevicePerformance;
import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;
import cn.net.sunet.sunetcloud.service.DevicePerformanceServiceImpl;
import cn.net.sunet.sunetcloud.service.MaintainMalfunctionServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping("/maintain")
@Api(value = "maintain",description = "维修故障管理")
public class MaintainController {
    @Autowired
    private MaintainMalfunctionServiceImpl maintainMalfunctionService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @Autowired
    private DevicePerformanceServiceImpl devicePerformanceService;
    @Autowired
    private Collection collection;
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public String accept(@RequestParam long id) {
        MaintainMalfunction maintainMalfunction=new MaintainMalfunction();
        maintainMalfunction.setId(id);
        maintainMalfunction.setSchedule(1);
        try {
            maintainMalfunctionService.update(maintainMalfunction);
        } catch (DataAccessException e) {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setContent(e).asJson();
        }
        ScheduledFuture<?> future=collection.getFutureHash().get(id);
        if (future != null) {
            future.cancel(true);
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).asJson();
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String complete(@RequestParam long id,
                           @RequestParam String description,
                           @RequestParam String method,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                   Date solvedTime) {
        MaintainMalfunction maintainMalfunction=new MaintainMalfunction();
        maintainMalfunction.setId(id);
        maintainMalfunction.setDescription(description);
        maintainMalfunction.setSolvedTime(solvedTime);
        maintainMalfunction.setMethod(method);
        maintainMalfunction.setSchedule(2);
        try {
            maintainMalfunctionService.update(maintainMalfunction);
        } catch (DataAccessException e) {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setContent(e).asJson();
        }
        MaintainMalfunction maintainMalfunction1 = maintainMalfunctionService.selectById(maintainMalfunction.getId());
        Date start_time = maintainMalfunction1.getStartTime();
        Date end_time = maintainMalfunction1.getSolvedTime();


        float timeInterval = (end_time.getTime() - start_time.getTime()) / (float) 3600000;
        DevicePerformance devicePerformance = devicePerformanceService.selectByDeviceId(maintainMalfunction1.getDeviceId
                ());
        devicePerformance.setMalfunctionTime(devicePerformance.getMalfunctionTime() + timeInterval);
        devicePerformance.setMalfunctionNumber(devicePerformance.getMalfunctionNumber() + 1);
        devicePerformance.setMttrTime((float) devicePerformance.getMalfunctionTime() / devicePerformance.getMalfunctionNumber
                ());
        devicePerformanceService.update(devicePerformance);
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).asJson();

    }
}
