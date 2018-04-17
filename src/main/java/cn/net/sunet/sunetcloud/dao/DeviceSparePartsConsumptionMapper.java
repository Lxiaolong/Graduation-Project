package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceSparePartsConsumption;

public interface DeviceSparePartsConsumptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceSparePartsConsumption record);

    int insertSelective(DeviceSparePartsConsumption record);

    DeviceSparePartsConsumption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceSparePartsConsumption record);

    int updateByPrimaryKey(DeviceSparePartsConsumption record);
}