package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainSpareParts;

public interface MaintainSparePartsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainSpareParts record);

    int insertSelective(MaintainSpareParts record);

    MaintainSpareParts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainSpareParts record);

    int updateByPrimaryKey(MaintainSpareParts record);
}