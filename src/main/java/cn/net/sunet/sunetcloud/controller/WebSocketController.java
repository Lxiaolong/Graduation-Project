package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/11
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.domain.Device;
import cn.net.sunet.sunetcloud.domain.DevicePerformance;
import cn.net.sunet.sunetcloud.service.DevicePerformanceServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@Controller
@EnableScheduling
public class WebSocketController {
    @Autowired
    private DeviceServiceImpl deviceService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    @SendTo("/topic/send")
    public String send(String s) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s;
    }

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/callback")
    public Object callback() throws Exception {
        // 发现消息
        List<Device> de = deviceService.queryStatus();
        HashMap hashMap = new HashMap();
        for (Device device : de) {
            if (device.getStatus() != null) {
                String key=device.getStatus();
                if (hashMap.containsKey(key)) {
                    hashMap.put(key,(Integer)hashMap.get(key)+1);
                }
                else {
                    hashMap.put(key,1);
                }
            }
        }
        Date date=new Date();
        HashMap hashMap1=new HashMap();
        hashMap1.put("time",date);
        hashMap1.put("data",hashMap);
        messagingTemplate.convertAndSend("/topic/callback", new JSONGenerator().createJSONGenerator().setContent
                (hashMap1).asJson());
        return "callback";
    }
    @SendTo("/topic/callback1")
    public Object callback1(long deviceId) throws Exception {
        // 发现消息

        messagingTemplate.convertAndSend("/topic/callback1", new JSONGenerator().createJSONGenerator().setContent
                (deviceId).asJson());
        return "callback1";
    }
}
