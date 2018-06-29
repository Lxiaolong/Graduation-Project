package cn.net.sunet.sunetcloud.domain;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/6/29
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import java.security.Principal;

/**
 * @author Lxiaolong
 */

public class StompPrincipe implements Principal {
    private String name;

    public StompPrincipe(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
