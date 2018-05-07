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

import java.util.HashMap;

/**
 * @author Lxiaolong
 */
@Service
public class DeviceServiceImpl {
    @Autowired
    private DeviceMapper deviceMapper;

    public int insert(Device device) {
        return deviceMapper.insert(device);

    }

    public Device selectById(long id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    public HashMap queryPage(int page, int count) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", deviceMapper.queryPage((page - 1) * count, count));
        hashMap.put("page_total", deviceMapper.queryTotal());
        return hashMap;
    }
    public void updateStatus(Device device){
        deviceMapper.updateByPrimaryKeySelective(device);
    }


}
