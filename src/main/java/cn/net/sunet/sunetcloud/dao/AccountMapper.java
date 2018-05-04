package cn.net.sunet.sunetcloud.dao;

import cn.net.sunet.sunetcloud.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account selectByUsername(String username);

    Account selectByPhone(String phone);

    Account selectByEmail(String email);

    List<Account> query(@Param("page") int page, @Param("count") int count);

    int updateLock(@Param("username") String username, @Param("flag") byte flag);

    @Cacheable(value = "account", key = "#root.methodName", unless = "#result eq null")
    int querytotal();

    int deleteByUsername(String username);

    List<Account> queryMaintenance();
}