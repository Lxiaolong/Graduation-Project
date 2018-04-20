package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/19
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.AccountTypeMapper;
import cn.net.sunet.sunetcloud.domain.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class AccountTypeServiceImpl {
    private final AccountTypeMapper accountType;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeMapper accountType) {
        this.accountType = accountType;
    }
    public List query(){
        return accountType.query();
    }
}
