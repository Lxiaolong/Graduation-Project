package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DevicePerformance;

public interface DevicePerformanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevicePerformance record);

    int insertSelective(DevicePerformance record);

    DevicePerformance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevicePerformance record);

    int updateByPrimaryKey(DevicePerformance record);

    DevicePerformance selectByDeviceId(Long id);


}