package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;

public interface MaintainAuxiliaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainAuxiliary record);

    int insertSelective(MaintainAuxiliary record);

    MaintainAuxiliary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainAuxiliary record);

    int updateByPrimaryKey(MaintainAuxiliary record);
}