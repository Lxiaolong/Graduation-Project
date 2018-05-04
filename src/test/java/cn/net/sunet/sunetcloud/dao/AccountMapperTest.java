package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Account;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/13
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
    @Autowired
    private AccountMapper serviceMapper;
    @Test
    public void query(){
        List<Account> services=serviceMapper.query(1,1);
        System.out.println(new JSONGenerator().setContent(services).asJson());
    }

}