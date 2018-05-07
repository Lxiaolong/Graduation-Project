package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceMapper;
import cn.net.sunet.sunetcloud.dao.DeviceRuntimeMapper;
import cn.net.sunet.sunetcloud.dao.ScheduleMapper;
import cn.net.sunet.sunetcloud.domain.DeviceRuntime;
import cn.net.sunet.sunetcloud.domain.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceRuntimeServiceImpl {
    private final DeviceRuntimeMapper deviceRuntimeMapper;
    private final ScheduleMapper scheduleMapper;
    private final DeviceMapper deviceMapper;
    @Autowired
    public DeviceRuntimeServiceImpl(DeviceRuntimeMapper deviceRuntimeMapper, ScheduleMapper scheduleMapper, DeviceMapper deviceMapper) {
        this.deviceRuntimeMapper = deviceRuntimeMapper;
        this.scheduleMapper = scheduleMapper;
        this.deviceMapper = deviceMapper;
    }
    public void insert(DeviceRuntime deviceRuntime) {
        deviceRuntimeMapper.insert(deviceRuntime);
    }

    public void update(DeviceRuntime deviceRuntime) {
        deviceRuntimeMapper.updateByPrimaryKeySelective(deviceRuntime);
    }

    public DeviceRuntime selectTestTime(Long deiviceId) {
        ArrayList<DeviceRuntime> list = deviceRuntimeMapper.selectByDeviceId(deiviceId);
        return list.get(0);
    }

    public List<DeviceRuntime> queryByTime(long deviceId, Date startTime, Date endTime) {
        return deviceRuntimeMapper.queryByTime(deviceId, startTime, endTime);
    }

    public List timeActivation(List<DeviceRuntime> list) {
        List<HashMap> result = new ArrayList();
        HashMap<String, Float> hashMap = new HashMap();
        HashMap hashMap1 = new HashMap();
        List<Schedule> schedules = scheduleMapper.selectByDeviceId(list.get(0).getDeviceId());
        float uph= deviceMapper.selectByPrimaryKey(list.get(0).getDeviceId()).getRatedUPH();
        float time=0;
        for (Schedule schedule:schedules){
            time+=schedule.getOperatinghours();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (DeviceRuntime deviceRuntime : list) {
            String key = sdf.format(deviceRuntime.getWorkTime());
            if (hashMap.containsKey(key)) {
                hashMap.put(key, (Float) hashMap.get(key) + deviceRuntime.getRuntime());
                hashMap1.put(key, (Long) hashMap1.get(key) + deviceRuntime.getRuntimeOutput());
            } else {
                hashMap.put(key, deviceRuntime.getRuntime());
                hashMap1.put(key, deviceRuntime.getRuntimeOutput());
            }
        }
        Iterator entries = hashMap.entrySet().iterator();
        for (Map.Entry<String, Float> entry : hashMap.entrySet()) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("date", entry.getKey());
            hashMap2.put("timeactivation", entry.getValue()/time);
            hashMap2.put("performanceactivation", ((long)hashMap1.get(entry.getKey())/time)/uph);
            result.add(hashMap2);
        }
        return result;
    }
}
