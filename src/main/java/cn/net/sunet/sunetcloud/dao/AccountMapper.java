package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account selectByUserName(String username);

    Account selectByPhone(String phone);

    Account selectByEmail(String email);
}