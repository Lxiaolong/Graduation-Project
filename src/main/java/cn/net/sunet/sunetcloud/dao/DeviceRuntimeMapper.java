package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceRuntime;

public interface DeviceRuntimeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceRuntime record);

    int insertSelective(DeviceRuntime record);

    DeviceRuntime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceRuntime record);

    int updateByPrimaryKey(DeviceRuntime record);
}