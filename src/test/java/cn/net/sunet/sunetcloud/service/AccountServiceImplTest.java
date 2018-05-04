package cn.net.sunet.sunetcloud.service;

import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/19
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
    @Autowired
    private AccountServiceImpl accountService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void query() throws Exception {
        for (int i = 0; i < 10; i++) {
            long start=System.currentTimeMillis();
            //HashMap list = accountService.queryPage(2, 10);
            int total=accountService.queryTotal();
            long ens=System.currentTimeMillis();
            logger.info(String.valueOf((ens-start)));
            logger.info(new JSONGenerator().createJSONGenerator().setContent(total).asJson());
        }
    }
    @Test
    public void delete(){
        System.out.println(new JSONGenerator().createJSONGenerator().setContent(accountService.delete(1,10,
                "mcaldicot0")).asJson());
    }
    @Test
    public void querymaintenance(){
        System.out.println(new JSONGenerator().createJSONGenerator().setContent(accountService.queryMaintenance()).asJson());
    }

}