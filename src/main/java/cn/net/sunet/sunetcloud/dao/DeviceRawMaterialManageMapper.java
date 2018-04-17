package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceRawMaterialManage;

public interface DeviceRawMaterialManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceRawMaterialManage record);

    int insertSelective(DeviceRawMaterialManage record);

    DeviceRawMaterialManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceRawMaterialManage record);

    int updateByPrimaryKey(DeviceRawMaterialManage record);
}