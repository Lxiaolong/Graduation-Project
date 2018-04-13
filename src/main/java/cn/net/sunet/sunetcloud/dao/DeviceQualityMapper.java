package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceQuality;

public interface DeviceQualityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceQuality record);

    int insertSelective(DeviceQuality record);

    DeviceQuality selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceQuality record);

    int updateByPrimaryKey(DeviceQuality record);
}