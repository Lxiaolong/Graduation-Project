package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/2
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.MaintainAuxiliaryMapper;
import cn.net.sunet.sunetcloud.dao.MaintainRawMaterialMapper;
import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;
import cn.net.sunet.sunetcloud.domain.MaintainRawMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class MaintainRawserviceImpl {
    @Autowired
    private MaintainRawMaterialMapper maintainRawMaterialMapper;
    @Autowired
    private MaintainAuxiliaryMapper maintainAuxiliaryMapper;
    public HashMap queryRA(){
        HashMap<String, List> hashMap=new HashMap<>();
        hashMap.put("main_material",maintainRawMaterialMapper.query());
        hashMap.put("other_material",maintainAuxiliaryMapper.query());
        return hashMap;
    }
    public HashMap querypage(int page,int count){
        HashMap hashMap=new HashMap<>();
        hashMap.put("data",maintainRawMaterialMapper.queryPage((page-1)*count,count));
        hashMap.put("page_total",maintainRawMaterialMapper.queryTotal());
        return hashMap;
    }
    public void insert(MaintainRawMaterial maintainRawMaterial){
        maintainRawMaterialMapper.insertSelective(maintainRawMaterial);
    }
    public List query(){
        return maintainRawMaterialMapper.query();
    }
    public void update(MaintainRawMaterial maintainRawMaterial){
        maintainRawMaterialMapper.updateByPrimaryKeySelective(maintainRawMaterial);
    }
    public void delete(long id){
        maintainRawMaterialMapper.deleteByPrimaryKey(id);
    }
}
