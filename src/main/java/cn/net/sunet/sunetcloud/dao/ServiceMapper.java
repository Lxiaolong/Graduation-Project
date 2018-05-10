package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    @Cacheable(value = "service", key = "#root.methodName", unless = "#result eq null")
    List<Service> query();

    List<Service> selectAll();
}