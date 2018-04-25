package cn.net.sunet.sunetcloud.utils;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/25
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

/**
 * @author Lxiaolong
 */

public class MyRunnle implements Runnable {
    private int flag=1;
    @Override
    public void run() {

        if (flag==0){
            System.out.println(flag);
        }
        if (flag==1){
            System.out.println(flag);
        }
        flag=0;
    }
}
