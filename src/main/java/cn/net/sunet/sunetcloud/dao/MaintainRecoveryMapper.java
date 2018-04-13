package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainRecovery;

public interface MaintainRecoveryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainRecovery record);

    int insertSelective(MaintainRecovery record);

    MaintainRecovery selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainRecovery record);

    int updateByPrimaryKey(MaintainRecovery record);
}