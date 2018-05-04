package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainAuxiliaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainAuxiliary record);

    int insertSelective(MaintainAuxiliary record);

    MaintainAuxiliary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainAuxiliary record);

    int updateByPrimaryKey(MaintainAuxiliary record);
    List<MaintainAuxiliary> query();
    List<MaintainAuxiliary> queryPage(@Param("page") int page,@Param("count") int count);
    int queryTotal();
}