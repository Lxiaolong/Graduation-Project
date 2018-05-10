package cn.net.sunet.sunetcloud.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceMapperTest {
    @Autowired
    private DeviceMapper deviceMapper;
    @Test
    public void selectIp() throws Exception {
        System.out.println(deviceMapper.selectIp((long)4));
    }

}