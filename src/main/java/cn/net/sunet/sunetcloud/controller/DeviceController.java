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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lxiaolong
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceService;
    @Autowired
    private JSONGenerator jsonGenerator;

    @RequestMapping(value = "/insert", method = RequestMethod.PUT)
    public String inset(@ModelAttribute Device device) {
        if (deviceService.insert(device)) {
            return jsonGenerator.setCode(Constant.SUCCESS).setMsg("添加成功").asJson();
        } else {
            return jsonGenerator.setCode(Constant.DATABASE_ERROR).setMsg("添加失败").asJson();
        }
    }
}
