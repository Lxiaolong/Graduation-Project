package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceRuntime;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DeviceRuntimeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceRuntime record);

    int insertSelective(DeviceRuntime record);

    DeviceRuntime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceRuntime record);

    int updateByPrimaryKey(DeviceRuntime record);

    public ArrayList<DeviceRuntime> selectByDeviceId(Long deiviceId);
    public List queryByTime(@Param("deviceId") Long deviceId, @Param("startTime") Date startTime,@Param("endTime") Date
            endTime);
}