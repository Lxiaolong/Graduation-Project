package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/3
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;
import cn.net.sunet.sunetcloud.service.MaintainAuxiliaryServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping(value = "/maintainauxiliary")
public class MaintainAuxiliaryController {
    @Autowired
    private MaintainAuxiliaryServiceImpl maintainAuxiliaryService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @RequestMapping(value = "/querypage",method = RequestMethod.GET)
    public String queryPage(@RequestParam int page,
                            @RequestParam int count){
        return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent
                (maintainAuxiliaryService.queryPage(page, count)).asJson();
    }
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(@ModelAttribute MaintainAuxiliary maintainAuxiliary){
        try {
            maintainAuxiliaryService.insert(maintainAuxiliary);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加成功").asJson();
        }
        catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("请检查数据").asJson();
        }

    }
    @RequestMapping(value = "/update",method =RequestMethod.PUT )
    public String update(@ModelAttribute MaintainAuxiliary maintainAuxiliary){
        try{
            maintainAuxiliaryService.update(maintainAuxiliary);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("修改成功").asJson();
        }catch (DataAccessException e){
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("修改失败").asJson();
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam int other_material_id){
        try{
            maintainAuxiliaryService.delete(other_material_id);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").asJson();
        }catch (DataAccessException e){
            return jsonGenerator.setStatus(Constant.SUCCESS).setMsg("删除失败").asJson();
        }
    }
}
