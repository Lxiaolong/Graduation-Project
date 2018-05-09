package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> queryPage(@Param("page") int page, @Param("count") int count);

    int queryTotal();

    List<Device> querySummary(@Param("deviceId") long deviceId,@Param("page") long page,@Param("count") int count);
}