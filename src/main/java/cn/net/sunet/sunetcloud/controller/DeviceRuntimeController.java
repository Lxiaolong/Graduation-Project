package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/2
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.DeviceRuntime;
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

import java.util.Date;
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

    @RequestMapping(value = "/queryByTime", method = RequestMethod.GET)
    public String queryByTime(@RequestParam long deviceId,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
        try{
            List<DeviceRuntime> deviceRuntimes=deviceRuntimeService.queryByTime(deviceId,startTime,endTime);
            if(deviceRuntimes.isEmpty()){
                return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询为空").asJson();
            }
            List<?> result=deviceRuntimeService.timeActivation(deviceRuntimes);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setContent(result).asJson();
        }
        catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("查询出错").asJson();
        }

    }
}
