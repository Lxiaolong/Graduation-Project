package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;
import cn.net.sunet.sunetcloud.domain.MaintainRawMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainRawMaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainRawMaterial record);

    int insertSelective(MaintainRawMaterial record);

    MaintainRawMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainRawMaterial record);

    int updateByPrimaryKey(MaintainRawMaterial record);

    List<MaintainRawMaterial> query();

    List<MaintainAuxiliary> queryPage(@Param("page") int page, @Param("count") int count);

    int queryTotal();

}