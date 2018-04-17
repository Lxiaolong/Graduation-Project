package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.RoleServiceControl;

public interface RoleServiceControlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleServiceControl record);

    int insertSelective(RoleServiceControl record);

    RoleServiceControl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleServiceControl record);

    int updateByPrimaryKey(RoleServiceControl record);
}