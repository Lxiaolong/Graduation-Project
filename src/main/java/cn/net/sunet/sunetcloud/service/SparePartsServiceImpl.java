package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/9
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceParePartsManageMapper;
import cn.net.sunet.sunetcloud.domain.DeviceParePartsManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class SparePartsServiceImpl {
    @Autowired
    private DeviceParePartsManageMapper sparePartsConsumptionMapper;

    public void insert(DeviceParePartsManage deviceRawMaterialConsumption) {
        sparePartsConsumptionMapper.insert(deviceRawMaterialConsumption);
    }
    public DeviceParePartsManage selectByDeviceTypeIdAnd(int deviceTypeId,long sparePartsId){
        return sparePartsConsumptionMapper.selectByDeviceTypeIdAnd(deviceTypeId, sparePartsId);
    }
}