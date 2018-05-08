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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    public void insert(DeviceQuality deviceQuality) {
        int firstNg = deviceQuality.getNgNumber() + deviceQuality.getErrorLoadingNumber() - deviceQuality.getLeakageNumber();
        int firstThrought = deviceQuality.getFeedNumber() - firstNg;
        deviceQuality.setRetestRate(deviceQuality.getRetestNumber() / (float) deviceQuality.getFeedNumber());
        deviceQuality.setLeakageRate(deviceQuality.getLeakageNumber() / (firstThrought / (float) deviceQuality
                .getFeedNumber() * deviceQuality.getRetestNumber()));
        deviceQuality.setThroughRate(deviceQuality.getDischargeNumber() / (float) deviceQuality.getFeedNumber());
        if (firstNg == 0) {
            deviceQuality.setErrorLoadingRate((float) 0.0);
        } else {
            deviceQuality.setErrorLoadingRate(deviceQuality.getErrorLoadingNumber() / ((firstNg / (float) deviceQuality
                    .getFeedNumber()) * deviceQuality.getRetestNumber()));
        }
        deviceQualityMapper.insert(deviceQuality);
    }

    public List<DeviceQuality> queryByTime(long deviceId, Date startTime, Date endTime) {
        return deviceQualityMapper.queryByTime(deviceId, startTime, endTime);
    }

}

