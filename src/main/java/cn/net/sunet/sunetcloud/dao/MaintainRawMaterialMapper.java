package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainRawMaterial;

public interface MaintainRawMaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainRawMaterial record);

    int insertSelective(MaintainRawMaterial record);

    MaintainRawMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainRawMaterial record);

    int updateByPrimaryKey(MaintainRawMaterial record);
}