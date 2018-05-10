package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceRawMaterialManage;
import org.apache.ibatis.annotations.Param;

public interface DeviceRawMaterialManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceRawMaterialManage record);

    int insertSelective(DeviceRawMaterialManage record);

    DeviceRawMaterialManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceRawMaterialManage record);

    int updateByPrimaryKey(DeviceRawMaterialManage record);

    DeviceRawMaterialManage selectByDeviceTypeIdAnd(@Param("deviceTypeId") int deviceTypeId, @Param("rawMaterialId") long
            rawMaterialId);
}