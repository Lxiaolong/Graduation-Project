package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

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
    private AccountMapper accountMapper;
    @Test
    public void insert() throws Exception {
        Account account=new Account();
        account.setUsername("1");
        account.setPassword("11");
        account.setTelephone("1");
        account.setEmail("1");
        account.setNickname("11");
        account.setEmployeeNumber(1);
        account.setGender("男");
        account.setAccountTypeId(1);
        account.setIsCheck((byte)0);
        System.out.println(accountMapper.insert(account)+"qqq");
    }

}