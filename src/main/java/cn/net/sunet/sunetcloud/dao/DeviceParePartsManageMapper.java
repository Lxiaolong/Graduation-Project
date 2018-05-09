package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceParePartsManage;
import org.apache.ibatis.annotations.Param;

public interface DeviceParePartsManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceParePartsManage record);

    int insertSelective(DeviceParePartsManage record);

    DeviceParePartsManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceParePartsManage record);

    int updateByPrimaryKey(DeviceParePartsManage record);
    DeviceParePartsManage selectByDeviceTypeIdAnd(@Param("deviceTypeId") int deviceTypeId, @Param("sparePartsId") long
            sparePartsId);
}