package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceMaterial;
import org.apache.ibatis.annotations.Param;

public interface DeviceMaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceMaterial record);

    int insertSelective(DeviceMaterial record);

    DeviceMaterial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceMaterial record);

    int updateByPrimaryKey(DeviceMaterial record);

    DeviceMaterial selectByDeviceTypeIdAnd(@Param("deviceTypeId") int deviceTypeId, @Param("materialId") int
            materialId);
}