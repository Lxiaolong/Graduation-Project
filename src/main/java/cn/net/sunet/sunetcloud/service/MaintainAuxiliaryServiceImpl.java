package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/3
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.MaintainAuxiliaryMapper;
import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class MaintainAuxiliaryServiceImpl {
    @Autowired
    private MaintainAuxiliaryMapper maintainAuxiliaryMapper;
    public HashMap queryPage(int page,int count){
        List<MaintainAuxiliary> list=maintainAuxiliaryMapper.queryPage((page-1)*count,count);
        HashMap hashMap=new HashMap();
        hashMap.put("data",list);
        hashMap.put("page_total",maintainAuxiliaryMapper.queryTotal());
        return hashMap;
    }
    public void insert(MaintainAuxiliary maintainAuxiliary){
        maintainAuxiliaryMapper.insert(maintainAuxiliary);
    }
    public void update(MaintainAuxiliary maintainAuxiliary){
        maintainAuxiliaryMapper.updateByPrimaryKeySelective(maintainAuxiliary);
    }
    public void delete(long id){
        maintainAuxiliaryMapper.deleteByPrimaryKey(id);
    }
}
