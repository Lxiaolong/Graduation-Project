package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceQualityMapper;
import cn.net.sunet.sunetcloud.domain.DeviceQuality;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceQualityServiceImpl {
    private final DeviceQualityMapper deviceQualityMapper;

    @Autowired
    public DeviceQualityServiceImpl(DeviceQualityMapper deviceQualityMapper) {
        this.deviceQualityMapper = deviceQualityMapper;
    }
    public int insert(DeviceQuality deviceQuality){
        return deviceQualityMapper.insert(deviceQuality);
    }
}
