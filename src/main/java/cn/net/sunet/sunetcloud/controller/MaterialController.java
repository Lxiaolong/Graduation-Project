package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/10
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.Material;
import cn.net.sunet.sunetcloud.domain.MaterialType;
import cn.net.sunet.sunetcloud.service.MaterialServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lxiaolong
 */
@RestController
@Api(description = "材料管理", value = "material")
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialServiceImpl materialService;
    @Autowired
    private JSONGenerator jsonGenerator;

    @RequestMapping(value = "/inset", method = RequestMethod.POST)
    public String insert(@ModelAttribute Material material) {
        if (material.getType() == null) {
            material.setType(1);
        }
        try {
            materialService.insert(material);
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("数据不规范，请检查").asJson();
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();
    }

    @RequestMapping(value = "/querybypage", method = RequestMethod.GET)
    public String queryByPage(@RequestParam(required = false) Integer typeId,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "10") int count) {
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent(materialService
                .queryByPage(typeId, page, count)).asJson();
    }

    @RequestMapping(value = "/querybyname", method = RequestMethod.GET)
    public String queryByName(@RequestParam String name,
                              @RequestParam(required = false) Integer typeId) {
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent
                (materialService.selectByname(name,typeId)).asJson();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute Material material) {
        try {
            materialService.update(material);
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("请检查更新数据").asJson();
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("更新成功").asJson();
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam int id){
        try{
            materialService.delete(id);
        }catch (DataAccessException e){
            return jsonGenerator.setStatus(Constant.DATABASE_ERROR).setMsg("删除失败").asJson();
        }
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").asJson();
    }

}
