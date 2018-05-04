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
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "accountquery", key = "#root.targetClass.toString() + #page", unless = "#result eq null")
    public HashMap queryPage(int page, int count) {
        List<HashMap> list = new ArrayList<>();


        List<Account> accounts = accountMapper.query((page - 1) * count, count);
        int total = accountMapper.querytotal();
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
            acunttype.put("typeId", account.getAccountType().getId().toString());
            hashMap.put("department", (HashMap) department.clone());
            hashMap.put("rank", (HashMap) acunttype.clone());
            hashMap.put("Islock", account.getIsLock());
            list.add((HashMap) hashMap.clone());
        }
        HashMap hashMap1 = new HashMap();
        hashMap1.put("data", list);
        hashMap1.put("page_total", total);
        Long end = System.currentTimeMillis();
        return hashMap1;
    }

    public int updateLock(String username, byte flag) {
        return accountMapper.updateLock(username, flag);
    }

    public int update(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    public void inset(Account account) {
        accountMapper.insertSelective(account);
    }

    public int queryTotal() {
        return accountMapper.querytotal();
    }

    public HashMap delete(int page,int count, String username) {
        accountMapper.deleteByUsername(username);
        return queryPage(page,count);
    }
    public HashMap queryMaintenance(){
        HashMap hashMap=new HashMap();
        hashMap.put("data",accountMapper.queryMaintenance());
        return hashMap;
    }

}
