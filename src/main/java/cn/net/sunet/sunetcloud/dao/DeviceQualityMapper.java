package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceQuality;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DeviceQualityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceQuality record);

    int insertSelective(DeviceQuality record);

    DeviceQuality selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceQuality record);

    int updateByPrimaryKey(DeviceQuality record);

    List<DeviceQuality> queryByTime(@Param("deviceId") long deviceId, @Param("startTime")Date startTime,@Param
            ("endTime") Date endTime);
}