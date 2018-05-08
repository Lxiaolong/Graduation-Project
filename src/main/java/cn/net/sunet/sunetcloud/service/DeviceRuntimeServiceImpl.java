package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceMapper;
import cn.net.sunet.sunetcloud.dao.DeviceQualityMapper;
import cn.net.sunet.sunetcloud.dao.DeviceRuntimeMapper;
import cn.net.sunet.sunetcloud.dao.ScheduleMapper;
import cn.net.sunet.sunetcloud.domain.DeviceQuality;
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
    private final DeviceQualityMapper deviceQualityMapper;
    @Autowired
    public DeviceRuntimeServiceImpl(DeviceRuntimeMapper deviceRuntimeMapper, ScheduleMapper scheduleMapper, DeviceMapper deviceMapper, DeviceQualityMapper deviceQualityMapper) {
        this.deviceRuntimeMapper = deviceRuntimeMapper;
        this.scheduleMapper = scheduleMapper;
        this.deviceMapper = deviceMapper;
        this.deviceQualityMapper = deviceQualityMapper;
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

    public List timeActivation(List<DeviceRuntime> list, List<DeviceQuality> deviceQualities) {
        List<HashMap> result = new ArrayList();
        HashMap<String, Float> hashMap = new HashMap();
        HashMap hashMap1 = new HashMap();
        HashMap hashMap2 = new HashMap();
        int sum = 0;
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
                hashMap.put(key, hashMap.get(key) + deviceRuntime.getRuntime());
                hashMap1.put(key, (Long) hashMap1.get(key) + deviceRuntime.getRuntimeOutput());
            } else {
                hashMap.put(key, deviceRuntime.getRuntime());
                hashMap1.put(key, deviceRuntime.getRuntimeOutput());
            }
        }
        for (DeviceQuality deviceQuality : deviceQualities) {
            String key = sdf.format(deviceQuality.getCollectionTime());
            if (hashMap2.containsKey(key)) {
                sum+=1;
                hashMap2.put(key, ((float)hashMap2.get(key)*(sum-1) + deviceQuality.getThroughRate())/sum);
            } else {
                sum=1;
                hashMap2.put(key, deviceQuality.getThroughRate()/1);

            }
        }
        Iterator entries = hashMap.entrySet().iterator();
        for (Map.Entry<String, Float> entry : hashMap.entrySet()) {
            float timeactivation=entry.getValue()/time;
            float performanceactivation=((long)hashMap1.get(entry.getKey())/time)/uph;
            float qualityactivation= (float) hashMap2.get(entry.getKey());
            float oee=timeactivation*performanceactivation*qualityactivation;
            HashMap hashMap3 = new HashMap();
            hashMap3.put("date", entry.getKey());
            hashMap3.put("timeactivation", timeactivation);
            hashMap3.put("performanceactivation", performanceactivation);
            hashMap3.put("qualityactivation",qualityactivation);
            hashMap3.put("oee",oee);
            result.add(hashMap3);
        }
        return result;
    }
}
