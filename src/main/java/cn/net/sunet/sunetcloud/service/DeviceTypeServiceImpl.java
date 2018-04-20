package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/17
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceTypeMapper;
import cn.net.sunet.sunetcloud.domain.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceTypeServiceImpl {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    public Boolean insert(DeviceType deviceType){
        int flag=deviceTypeMapper.insert(deviceType);
        if(flag==1){
            return true;
        }
        else {
            return false;
        }
    }
    public List<DeviceType> query(){
        return deviceTypeMapper.query();
    }
}
