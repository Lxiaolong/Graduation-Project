package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceMaterialMapper;
import cn.net.sunet.sunetcloud.domain.DeviceMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceTypeMaterialServiceImpl {
    @Autowired
    private DeviceMaterialMapper deviceMaterialMapper;
    public void insert(DeviceMaterial deviceMaterial){
        deviceMaterialMapper.insert(deviceMaterial);
    }
    public DeviceMaterial selectBydeviceIdAnd(int deivceId,int materialId){
        return deviceMaterialMapper.selectByDeviceTypeIdAnd(deivceId,materialId);
    }
}
