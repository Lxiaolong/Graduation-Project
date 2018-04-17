package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceAuxiliaryManage;

public interface DeviceAuxiliaryManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceAuxiliaryManage record);

    int insertSelective(DeviceAuxiliaryManage record);

    DeviceAuxiliaryManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceAuxiliaryManage record);

    int updateByPrimaryKey(DeviceAuxiliaryManage record);
}