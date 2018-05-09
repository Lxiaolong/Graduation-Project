package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/23
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DevicePerformanceMapper;
import cn.net.sunet.sunetcloud.domain.DevicePerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class DevicePerformanceServiceImpl {
    @Autowired
    private DevicePerformanceMapper devicePerformanceMapper;
    public DevicePerformance selectByDeviceId(Long id){
        return devicePerformanceMapper.selectByDeviceId(id);
    }
    public void update(DevicePerformance devicePerformance){
        devicePerformanceMapper.updateByPrimaryKeySelective(devicePerformance);
    }
    public void insert(DevicePerformance devicePerformance){
        devicePerformanceMapper.insertSelective(devicePerformance);
    }
    public DevicePerformance queryBydeviceId(long deviceId){
        return devicePerformanceMapper.selectByDeviceId(deviceId);
    }
}
