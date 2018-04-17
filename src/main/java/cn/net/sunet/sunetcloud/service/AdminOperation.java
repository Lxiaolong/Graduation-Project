package cn.net.sunet.sunetcloud.service;

import cn.net.sunet.sunetcloud.dao.AccountMapper;
import cn.net.sunet.sunetcloud.domain.Account;
import cn.net.sunet.sunetcloud.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminOperation {
    private final AccountMapper accountMapper;

    @Autowired
    public AdminOperation(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public int insertAccount(Account account) throws DatabaseException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(password);

        int flag = accountMapper.insert(account);
        return flag;
    }
}
