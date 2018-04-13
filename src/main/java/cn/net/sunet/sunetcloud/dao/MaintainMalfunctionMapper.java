package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;

public interface MaintainMalfunctionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainMalfunction record);

    int insertSelective(MaintainMalfunction record);

    MaintainMalfunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainMalfunction record);

    int updateByPrimaryKey(MaintainMalfunction record);
}