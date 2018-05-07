package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Schedule;

import java.util.List;

public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    public List<Schedule> selectByDeviceId(Long deviceId);
}