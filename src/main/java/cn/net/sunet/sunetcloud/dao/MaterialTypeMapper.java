package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaterialType;

import java.util.List;

public interface MaterialTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialType record);

    int insertSelective(MaterialType record);

    MaterialType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialType record);

    int updateByPrimaryKey(MaterialType record);

    List<MaterialType> query();
}