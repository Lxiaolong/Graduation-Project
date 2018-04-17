package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/13
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.DeviceMapper;
import cn.net.sunet.sunetcloud.dao.DeviceTypeMapper;
import cn.net.sunet.sunetcloud.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceServiceImpl {
    @Autowired
    private DeviceMapper deviceMapper;

    public Boolean insert(Device device) {
        int flag = deviceMapper.insert(device);
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

}
