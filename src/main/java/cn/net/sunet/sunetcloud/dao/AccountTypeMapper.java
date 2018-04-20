package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.AccountType;

import java.util.List;

public interface AccountTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountType record);

    int insertSelective(AccountType record);

    AccountType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountType record);

    int updateByPrimaryKey(AccountType record);

    List<AccountType> query();
}