package cn.net.sunet.sunetcloud.config;


import cn.net.sunet.sunetcloud.domain.Account;
import cn.net.sunet.sunetcloud.exception.UsernameIsExitedException;
import cn.net.sunet.sunetcloud.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountServiceImpl userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.selectByUserName(username);
        if (user == null) {
            user=userRepository.selectByPhone(username);
            if (user==null){
                user=userRepository.selectByEmail(username);
                if (user==null){
                    throw new UsernameIsExitedException("该用户不存在");
                }
            }
        }
        ArrayList<GrantedAuthorityImpl> roles= new ArrayList<GrantedAuthorityImpl>();
        roles.add(new GrantedAuthorityImpl(user.getAvatarUrl()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                roles);

    }
}
