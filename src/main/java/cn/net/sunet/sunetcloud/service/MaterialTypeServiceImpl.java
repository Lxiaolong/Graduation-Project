package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.MaterialTypeMapper;
import cn.net.sunet.sunetcloud.domain.MaterialType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class MaterialTypeServiceImpl {
    @Autowired
    private MaterialTypeMapper materialTypeMapper;
    public void insert(MaterialType materialType){
        materialTypeMapper.insert(materialType);
    }
    public void delete(int id){
        materialTypeMapper.deleteByPrimaryKey(id);
    }
    public void update(MaterialType materialType){
        materialTypeMapper.updateByPrimaryKeySelective(materialType);
    }
    public List<MaterialType> query(){
        return materialTypeMapper.query();
    }
}
