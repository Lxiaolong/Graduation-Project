package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/13
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.AccountMapper;
import cn.net.sunet.sunetcloud.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class AccountServiceImpl {
    @Autowired
    private AccountMapper accountMapper;

    public Account selectByUserName(String username) {
        return accountMapper.selectByUsername(username);
    }

    public Account selectByPhone(String phone) {
        return accountMapper.selectByUsername(phone);
    }

    public Account selectByEmail(String email) {
        return accountMapper.selectByUsername(email);
    }
}
