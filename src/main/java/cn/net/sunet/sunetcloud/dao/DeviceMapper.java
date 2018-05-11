package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

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

    String selectIp(Long ip);
@Cacheable(value = "device",key = "'status'",unless = "#result eq null")
    List<Device> queryStatus();
}