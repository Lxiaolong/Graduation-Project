package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.MaterialType;
import cn.net.sunet.sunetcloud.service.MaterialTypeServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lxiaolong
 */
@RestController
@Api(description = "材料种类管理", value = "materialtype")
@RequestMapping("/materialtype")
public class MaterialTypeController {
    @Autowired
    private MaterialTypeServiceImpl materialTypeService;
    @Autowired
    private JSONGenerator jsonGenerator;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String typeInsert(@RequestParam String name) {
        MaterialType materialType = new MaterialType();
        materialType.setName(name);
        materialTypeService.insert(materialType);
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam String name,
                         @RequestParam int id) {
        MaterialType materialType = new MaterialType();
        materialType.setName(name);
        materialType.setId(id);
        materialTypeService.update(materialType);
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("修改成功").asJson();
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam int id) {
        materialTypeService.delete(id);
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").asJson();
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {

        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功")
                .setContent(materialTypeService.query()).asJson();
    }

}
