package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/17
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.DeviceType;
import cn.net.sunet.sunetcloud.service.DeviceTypeServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping(value = "/devicetype")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeServiceImpl deviceTypeService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(){
        List<DeviceType> typeList=deviceTypeService.query();
        if (!typeList.isEmpty()){
            return jsonGenerator.setCode(Constant.SUCCESS).setMsg("查询成功").setData(typeList).asJson();
        }
        else {
            return jsonGenerator.setCode(Constant.DATABASE_ERROR).setMsg("吴查询结果").asJson();
        }
    }

}
