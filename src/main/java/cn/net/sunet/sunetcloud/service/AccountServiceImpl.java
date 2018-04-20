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
import cn.net.sunet.sunetcloud.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List query() {
        List<HashMap> list = new ArrayList<>();


        List<Account> accounts = accountMapper.query();
        HashMap hashMap = new HashMap();
        HashMap department = new HashMap<>();
        HashMap acunttype = new HashMap<>();
        Long stat = System.currentTimeMillis();
        for (Account account : accounts) {
            hashMap.put("nickname", account.getNickname());
            hashMap.put("username", account.getUsername());
            hashMap.put("sex", account.getGender());
            hashMap.put("email", account.getEmail());
            hashMap.put("telephone", account.getTelephone());
            hashMap.put("jobNumber", account.getEmployeeNumber());
            department.put("departmentDesc", account.getDepartment().getName());
            department.put("departmentId", account.getDepartment().getId());
            acunttype.put("accountType", account.getAccountType().getName());
            acunttype.put("departmentId", account.getAccountType().getId().toString());
            hashMap.put("department", (HashMap) department.clone());
            hashMap.put("rank", (HashMap) acunttype.clone());
            list.add((HashMap) hashMap.clone());
        }
        Long end = System.currentTimeMillis();
        return list;
    }

    public int updateLock(String username,byte flag){
        return accountMapper.updateLock(username,flag);
    }
    public int update(Account account){
        return accountMapper.updateByPrimaryKeySelective(account);
    }
    public void inset(Account account){
        accountMapper.insertSelective(account);
    }

}
