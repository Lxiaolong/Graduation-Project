package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceConditions;

public interface DeviceConditionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceConditions record);

    int insertSelective(DeviceConditions record);

    DeviceConditions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceConditions record);

    int updateByPrimaryKey(DeviceConditions record);
}