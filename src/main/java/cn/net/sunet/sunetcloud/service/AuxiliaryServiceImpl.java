package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/9
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceAuxiliaryConsumptionMapper;
import cn.net.sunet.sunetcloud.dao.DeviceAuxiliaryManageMapper;
import cn.net.sunet.sunetcloud.domain.DeviceAuxiliaryConsumption;
import cn.net.sunet.sunetcloud.domain.DeviceAuxiliaryManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class AuxiliaryServiceImpl {
    @Autowired
    private DeviceAuxiliaryManageMapper auxiliaryManageMapper;

    public void insert(DeviceAuxiliaryManage auxiliaryManage) {
        auxiliaryManageMapper.insert(auxiliaryManage);
    }

    public DeviceAuxiliaryManage selectByDeviceTypeIdAnd(int deivceTypeId, long auxiliaryId) {
        return auxiliaryManageMapper.selectByDeviceTypeIdAnd(deivceTypeId, auxiliaryId);
    }

}
