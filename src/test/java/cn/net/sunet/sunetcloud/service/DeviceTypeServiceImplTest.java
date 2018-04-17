package cn.net.sunet.sunetcloud.service;

import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/17
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceTypeServiceImplTest {
    @Autowired
    private DeviceTypeServiceImpl deviceTypeService;
    @Test
    public void query() throws Exception {
        System.out.println(new JSONGenerator().setData(deviceTypeService.query()).asJson());
    }

}