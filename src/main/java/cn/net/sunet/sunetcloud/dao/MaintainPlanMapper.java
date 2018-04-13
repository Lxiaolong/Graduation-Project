package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainPlan;

public interface MaintainPlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainPlan record);

    int insertSelective(MaintainPlan record);

    MaintainPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainPlan record);

    int updateByPrimaryKey(MaintainPlan record);
}