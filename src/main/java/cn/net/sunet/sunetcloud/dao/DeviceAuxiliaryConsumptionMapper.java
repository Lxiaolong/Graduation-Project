package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceAuxiliaryConsumption;

public interface DeviceAuxiliaryConsumptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceAuxiliaryConsumption record);

    int insertSelective(DeviceAuxiliaryConsumption record);

    DeviceAuxiliaryConsumption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceAuxiliaryConsumption record);

    int updateByPrimaryKey(DeviceAuxiliaryConsumption record);
}