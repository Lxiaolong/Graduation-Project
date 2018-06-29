package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/11
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.config.SystemWebsocketHandler;
import cn.net.sunet.sunetcloud.service.DeviceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Lxiaolong
 */
@Controller
@EnableScheduling
public class WebSocketController {
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeviceServiceImpl deviceService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Bean
    public SystemWebsocketHandler systemWebsocketHandler(){
        return new SystemWebsocketHandler();
    }

    @MessageMapping("/send")

    public void send(String s,Principal principal) throws Exception {
        System.out.println(s+principal.getName());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(messagingTemplate.getUserDestinationPrefix()+"--"+messagingTemplate.getMessageChannel());
        systemWebsocketHandler().sendMessageToUser(principal.getName(),new TextMessage(s));
        messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/send",df);

    }
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

/*    @Scheduled(fixedRate = 1000)
    //@SendToUser("/topic/callback")
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
        messagingTemplate.convertAndSendToUser("15581311816","/topic/callback", new JSONGenerator()
                .createJSONGenerator
                ().setContent
                (hashMap1).asJson());
        return "callback";
    }
    @SendTo("/topic/callback1")
    public Object callback1(long deviceId) throws Exception {
        // 发现消息

        messagingTemplate.convertAndSend("/topic/callback1", new JSONGenerator().createJSONGenerator().setContent
                (deviceId).asJson());
        return "callback1";
    }*/
}
