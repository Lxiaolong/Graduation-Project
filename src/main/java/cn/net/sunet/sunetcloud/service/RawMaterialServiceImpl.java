package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/9
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceRawMaterialConsumptionMapper;
import cn.net.sunet.sunetcloud.dao.DeviceRawMaterialManageMapper;
import cn.net.sunet.sunetcloud.domain.DeviceRawMaterialConsumption;
import cn.net.sunet.sunetcloud.domain.DeviceRawMaterialManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class RawMaterialServiceImpl {
    @Autowired
    DeviceRawMaterialManageMapper rawMaterialManageMapper;

    public void insert(DeviceRawMaterialManage rawMaterialManage) {
        rawMaterialManageMapper.insert(rawMaterialManage);
    }

    public DeviceRawMaterialManage selectByDeviceTypeIdAnd(int deviceTypeId, long rawMaterialId) {
        return rawMaterialManageMapper.selectByDeviceTypeIdAnd(deviceTypeId, rawMaterialId);
    }
}
