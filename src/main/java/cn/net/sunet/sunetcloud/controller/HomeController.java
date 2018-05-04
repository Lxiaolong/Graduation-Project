package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/4
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Lxiaolong
 */
@Controller
public class HomeController {
    @GetMapping("login_1")
    public String login_1(){
        return "index";
    }
}
