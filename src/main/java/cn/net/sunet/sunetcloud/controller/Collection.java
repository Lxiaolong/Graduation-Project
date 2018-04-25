package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.*;
import cn.net.sunet.sunetcloud.exception.DatabaseException;
import cn.net.sunet.sunetcloud.service.*;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import cn.net.sunet.sunetcloud.utils.SendEmail;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

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
    DevicePerformanceServiceImpl devicePerformanceService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @Autowired
    private MaintainMalfunctionServiceImpl maintainMalfunctionService;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     */

    private ScheduledFuture<?> future;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

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
        DeviceRuntime deviceRuntime1 = deviceRuntimeService.selectTestTime(deviceRuntime.getDeviceId());
        deviceRuntime.setId(deviceRuntime1.getId());
        deviceRuntime.setAdditiveOutput(deviceRuntime1.getAdditiveOutput() + deviceRuntime.getRuntimeOutput());
        try {

            deviceRuntimeService.update(deviceRuntime);

        } catch (DataAccessException e) {
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg(e.getMessage()).setData(e).asJson();
        }
        float runtime = (deviceRuntime.getDownTime().getTime() - deviceRuntime1.getWorkTime().getTime()) / (float) 3600000;
        try {
            DevicePerformance devicePerformance = devicePerformanceService.selectByDeviceId(deviceRuntime1.getDeviceId());
            devicePerformance.setRunTime(devicePerformance.getRunTime() + runtime);
            devicePerformanceService.update(devicePerformance);
        } catch (DataAccessException e) {
            return "notok";
        }
        return "ok";
    }

    @RequestMapping(value = "/devicemaintain", method = RequestMethod.POST)
    public String maintain(@RequestParam long deviceId,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
                                   Date startTime,
                           @RequestParam String code) {
        MaintainMalfunction maintainMalfunction=new MaintainMalfunction();
        maintainMalfunction.setDeviceId(deviceId);
        maintainMalfunction.setStartTime(startTime);
        maintainMalfunction.setCode(code);
        maintainMalfunction.setSchedule(0);
        Calendar now = Calendar.getInstance();
        int hour=(now.get(Calendar.HOUR_OF_DAY)+1)%24;
        now.add(Calendar.SECOND,+5);
        String cron=now.get(Calendar.SECOND)+" "+now.get(Calendar.MINUTE)+" "+now.get
                (Calendar.HOUR_OF_DAY) +"," + ""+hour+" "+now.get(Calendar.DAY_OF_MONTH)+" "+(now.get(Calendar.MONTH)+1)+" "+"?";
        try {
            int key = maintainMalfunctionService.insert(maintainMalfunction);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return "notok";
        }
        MaintainMalfunction maintainMalfunction1 = maintainMalfunctionService.selectById(maintainMalfunction.getId());
        Calendar now1 = Calendar.getInstance();
        int minute = now1.get(Calendar.MINUTE) + 1;
        future = threadPoolTaskScheduler.schedule(new SendEmail(javaMailSender, maintainMalfunction1), new
                CronTrigger(cron));
        System.out.println("DynamicTaskController.startCron()");
        return "startTask";
    }

    @RequestMapping(value = "devicemaintain/accept", method = RequestMethod.POST)
    public String accept(@RequestParam long id) {
        MaintainMalfunction maintainMalfunction=new MaintainMalfunction();
        maintainMalfunction.setId(id);
        maintainMalfunction.setSchedule(1);
        try {
            maintainMalfunctionService.update(maintainMalfunction);
        } catch (DataAccessException e) {
            return "notok";
        }
        if (future != null) {
            future.cancel(true);
        }
        return "stopTask";
    }

    @RequestMapping(value = "/devicemaintain/complete", method = RequestMethod.POST)
    public String complete(@RequestParam long id,
                           @RequestParam String description,
                           @RequestParam String method,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
                                       Date solvedTime) {
        MaintainMalfunction maintainMalfunction=new MaintainMalfunction();
        maintainMalfunction.setId(id);
        maintainMalfunction.setDescription(description);
        maintainMalfunction.setSolvedTime(solvedTime);
        maintainMalfunction.setMethod(method);
        try {
            maintainMalfunctionService.update(maintainMalfunction);
        } catch (DataAccessException e) {
            return "notok";
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
        return "ok";

    }

}