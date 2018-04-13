package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.DeviceExpense;

public interface DeviceExpenseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceExpense record);

    int insertSelective(DeviceExpense record);

    DeviceExpense selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceExpense record);

    int updateByPrimaryKey(DeviceExpense record);
}