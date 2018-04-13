package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainSparePartsType;

public interface MaintainSparePartsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaintainSparePartsType record);

    int insertSelective(MaintainSparePartsType record);

    MaintainSparePartsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaintainSparePartsType record);

    int updateByPrimaryKey(MaintainSparePartsType record);
}