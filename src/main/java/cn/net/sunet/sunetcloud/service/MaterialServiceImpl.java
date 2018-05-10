package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.MaterialMapper;
import cn.net.sunet.sunetcloud.domain.Material;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class MaterialServiceImpl {
    @Autowired
    private MaterialMapper materialMapper;
    public void insert(Material material){
        materialMapper.insert(material);
    }
    public void update(Material material){
        materialMapper.updateByPrimaryKeySelective(material);
    }
    public void delete(int id){
        materialMapper.deleteByPrimaryKey(id);
    }
    public List<Material> queryByPage(Integer typeId, int page, int count){
        return materialMapper.queryByPage(typeId, (page-1)*count, count);
    }
    public List<Material> selectByname(String name,Integer typeId){
        return materialMapper.selectByName(name,typeId);
    }
}



