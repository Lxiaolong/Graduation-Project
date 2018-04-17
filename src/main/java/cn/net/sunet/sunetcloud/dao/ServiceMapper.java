package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Service;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
    List<Service> query();

}