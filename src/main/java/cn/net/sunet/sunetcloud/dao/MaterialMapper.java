package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Material;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> queryByPage(@Param("typeId") Integer typeId, @Param("page") int page,@Param("count") int count);

    List<Material> selectByName(@Param("name") String name,@Param("typeId") Integer typeId);
}