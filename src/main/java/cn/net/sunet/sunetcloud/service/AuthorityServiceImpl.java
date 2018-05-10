package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.AccountTypeMapper;
import cn.net.sunet.sunetcloud.dao.RoleServiceControlMapper;
import cn.net.sunet.sunetcloud.dao.ServiceMapper;
import cn.net.sunet.sunetcloud.domain.AccountType;
import cn.net.sunet.sunetcloud.domain.RoleServiceControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class AuthorityServiceImpl {
    @Autowired
    private RoleServiceControlMapper roleServiceControlMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private AccountTypeMapper accountTypeMapper;


    public void insert(RoleServiceControl roleServiceControl) {
        roleServiceControlMapper.insert(roleServiceControl);
    }

    public void delete(int id) {
        roleServiceControlMapper.deleteByPrimaryKey(id);
    }

    public List<RoleServiceControl> query(int page, int count) {
        return roleServiceControlMapper.query((page - 1) * count, count);
    }

    public void urlInsert(String url, String name) {
        cn.net.sunet.sunetcloud.domain.Service service = new cn.net.sunet.sunetcloud.domain.Service();
        service.setUrl(url);
        service.setName(name);
        serviceMapper.insert(service);
    }

    public void roleInsert(String role, String name) {
        AccountType accountType = new AccountType();
        accountType.setName(name);
        accountType.setRoles(role);
        accountTypeMapper.insertSelective(accountType);
    }

    public void roleDelete(int id) {

        accountTypeMapper.deleteByPrimaryKey(id);
    }
    public List<AccountType> queryRole(){
        return accountTypeMapper.query();
    }

    public List<cn.net.sunet.sunetcloud.domain.Service> queryUrl(){
        return serviceMapper.selectAll();
    }
    public HashMap selectAll(){
        List list=queryRole();
        List list1=queryUrl();
        HashMap hashMap=new HashMap();
        hashMap.put("roles",list);
        hashMap.put("url",list1);
        return hashMap;
    }
}
