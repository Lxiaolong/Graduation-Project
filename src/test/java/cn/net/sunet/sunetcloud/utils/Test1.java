package cn.net.sunet.sunetcloud.utils;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/7
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import org.junit.Test;

import java.util.Calendar;

/**
 * @author Lxiaolong
 */

public class Test1 {
    @Test
    public void test(){
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
    }
}
