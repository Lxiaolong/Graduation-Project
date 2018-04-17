package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceRawMaterialConsumption;

public interface DeviceRawMaterialConsumptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceRawMaterialConsumption record);

    int insertSelective(DeviceRawMaterialConsumption record);

    DeviceRawMaterialConsumption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceRawMaterialConsumption record);

    int updateByPrimaryKey(DeviceRawMaterialConsumption record);
}