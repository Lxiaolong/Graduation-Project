package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;
import cn.net.sunet.sunetcloud.utils.CreatePdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/25
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintainMalfunctionMapperTest {
    @Autowired
    private MaintainMalfunctionMapper maintainMalfunctionMapper;
    @Test
    public void selectById() throws Exception {
        MaintainMalfunction maintainMalfunction=maintainMalfunctionMapper.selectById((long)121);
        System.out.println(maintainMalfunction.toString());
        //new CreatePdf().createPdf(maintainMalfunction);
    }

}