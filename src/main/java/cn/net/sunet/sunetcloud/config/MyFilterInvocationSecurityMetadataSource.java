package cn.net.sunet.sunetcloud.config;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/16
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.ServiceMapper;
import cn.net.sunet.sunetcloud.domain.AccountType;
import cn.net.sunet.sunetcloud.domain.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author Lxiaolong
 */
@org.springframework.stereotype.Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private ServiceMapper urlMapper;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("login".equals(requestUrl)) {
            return null;
        }
        List<Service> urls = urlMapper.query();
        for (Service url : urls) {
            if (antPathMatcher.match(url.getUrl(), requestUrl) && url.getAccountTypes().size() > 0) {
                List<AccountType> accountTypes = url.getAccountTypes();
                int size = accountTypes.size();
                String[] roles = new String[size];
                for (int i = 0; i < size; i++) {
                    roles[i] = accountTypes.get(i).getRoles();
                }
                return SecurityConfig.createList(roles);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
