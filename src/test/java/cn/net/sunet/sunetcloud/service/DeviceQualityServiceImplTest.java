package cn.net.sunet.sunetcloud.service;

import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/8
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceQualityServiceImplTest {
    @Autowired
    private DeviceQualityServiceImpl deviceQualityService;
    @Test
    public void queryByTime() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start=formatter.parse("2018-05-12 00:00:00");
        Date end=formatter.parse("2018-05-13 00:00:00");
        System.out.println(new JSONGenerator().createJSONGenerator().setContent(deviceQualityService.queryByTime(1,
                start,end)).asJson());
    }

}