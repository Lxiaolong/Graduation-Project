package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceAuxiliaryManage;
import cn.net.sunet.sunetcloud.domain.DeviceRawMaterialManage;
import org.apache.ibatis.annotations.Param;

public interface DeviceAuxiliaryManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceAuxiliaryManage record);

    int insertSelective(DeviceAuxiliaryManage record);

    DeviceAuxiliaryManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceAuxiliaryManage record);

    int updateByPrimaryKey(DeviceAuxiliaryManage record);

    DeviceAuxiliaryManage selectByDeviceTypeIdAnd(@Param("deviceTypeId") int deviceTypeId, @Param("auxiliaryId") long
            auxiliaryId);
}