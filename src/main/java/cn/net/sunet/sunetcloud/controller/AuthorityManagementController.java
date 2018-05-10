package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.RoleServiceControl;
import cn.net.sunet.sunetcloud.service.AuthorityServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lxiaolong
 */
@RestController
@Api(description = "权限管理", value = "Authority")
@RequestMapping("/Authority")
public class AuthorityManagementController {
    @Autowired
    private JSONGenerator jsonGenerator;
    @Autowired
    private AuthorityServiceImpl authorityService;

    @RequestMapping(value = "/urlrole/insert", method = RequestMethod.POST)
    public String urlRoleInsert(@RequestParam int urlId,
                                @RequestParam long accountId) {
        RoleServiceControl roleServiceControl = new RoleServiceControl();
        roleServiceControl.setAccountId(accountId);
        roleServiceControl.setServiceId(urlId);
        authorityService.insert(roleServiceControl);
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();
    }
    @RequestMapping(value = "/urlRole/delete", method = RequestMethod.DELETE)
    public String urlRoleDelete(@RequestParam int id) {
        try {
            authorityService.delete(id);
        }catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("请先移除该权限的人员").asJson();
        }

        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").asJson();
    }

    @RequestMapping(value = "/urlRole/query", method = RequestMethod.GET)
    public String query(@RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int count) {
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent(authorityService
                .query(page, count)).asJson();
    }

    @RequestMapping(value = "/url/insert", method = RequestMethod.POST)
    public String urlInsert(@RequestParam String url,
                            @RequestParam(required = false) String name) {
        try {
            authorityService.urlInsert(url, name);
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("该url以存在").asJson();
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();

    }

    @RequestMapping(value = "/role/insert", method = RequestMethod.POST)
    public String roleInsert(@RequestParam String role,
                             @RequestParam(required = false) String name) {
        try {
            authorityService.roleInsert(role, name);
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("该权限以存在").asJson();
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();

    }

    @RequestMapping(value = "/role/delete", method = RequestMethod.DELETE)
    public String roleDelete(@RequestParam int id) {

        authorityService.roleDelete(id);

        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").asJson();

    }

    @RequestMapping(value = "/urlRole/selectall", method = RequestMethod.GET)
    public String selectAll() {
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent
                (authorityService.selectAll()).asJson();

    }

}
