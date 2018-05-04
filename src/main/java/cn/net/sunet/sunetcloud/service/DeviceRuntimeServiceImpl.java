package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceRuntimeMapper;
import cn.net.sunet.sunetcloud.domain.DeviceRuntime;
import cn.net.sunet.sunetcloud.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceRuntimeServiceImpl {
    private final DeviceRuntimeMapper deviceRuntimeMapper;

    @Autowired
    public DeviceRuntimeServiceImpl(DeviceRuntimeMapper deviceRuntimeMapper) {
        this.deviceRuntimeMapper = deviceRuntimeMapper;
    }

    public void insert(DeviceRuntime deviceRuntime)  {
        deviceRuntimeMapper.insert(deviceRuntime);
    }
    public void update(DeviceRuntime deviceRuntime){
        deviceRuntimeMapper.updateByPrimaryKeySelective(deviceRuntime);
    }
    public DeviceRuntime selectTestTime(Long deiviceId){
        ArrayList<DeviceRuntime> list=deviceRuntimeMapper.selectByDeviceId(deiviceId);
        return list.get(0);
    }
    public List queryByTime(long deviceId, Date startTime,Date endTime){
        return deviceRuntimeMapper.queryByTime(deviceId,startTime,endTime);
    }
}
