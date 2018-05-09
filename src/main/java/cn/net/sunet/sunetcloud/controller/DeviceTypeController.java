package cn.net.sunet.sunetcloud.controller;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/17
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.constant.Constant;
import cn.net.sunet.sunetcloud.domain.DeviceParePartsManage;
import cn.net.sunet.sunetcloud.domain.DeviceSparePartsConsumption;
import cn.net.sunet.sunetcloud.domain.DeviceType;
import cn.net.sunet.sunetcloud.service.AccountTypeServiceImpl;
import cn.net.sunet.sunetcloud.service.DeviceTypeServiceImpl;
import cn.net.sunet.sunetcloud.service.SparePartsServiceImpl;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Lxiaolong
 */
@RestController
@RequestMapping(value = "/devicetype")
@Api(value = "devicetype", description = "设备种类管理")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeServiceImpl deviceTypeService;
    @Autowired
    private AccountTypeServiceImpl accountTypeService;
    @Autowired
    private JSONGenerator jsonGenerator;
    @Autowired
    private SparePartsServiceImpl sparePartsService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        List<DeviceType> typeList = deviceTypeService.query();
        //List<AccountType> accountTypes = accountTypeService.queryPage();
        HashMap hashMap = new HashMap();
        //hashMap.put("rank", accountTypes);
        hashMap.put("data", typeList);
        if (!hashMap.isEmpty()) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("查询成功").setContent(hashMap)
                    .asJson();
        } else {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.OTHER_ERROR).setMsg("无查询结果").asJson();
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute DeviceType deviceType) {
        try {
            deviceTypeService.insert(deviceType);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("添加部门成功").asJson();
        } catch (DataAccessException e) {
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("添加部门失败").asJson();
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam int deviceTypeId){
        try {
            deviceTypeService.delete(deviceTypeId);
            return jsonGenerator.createJSONGenerator().setStatus(Constant.SUCCESS).setMsg("删除成功").asJson();
        }catch (DataAccessException e){
            return jsonGenerator.createJSONGenerator().setStatus(Constant.DATABASE_ERROR).setMsg("请检查相关信息后再删除")
                    .asJson();
        }
    }
    @RequestMapping(value = "/configmaterial",method = RequestMethod.POST)
    public String configMaterial(@RequestParam int deviceTypeId,
                                 @RequestParam(required = false,defaultValue = "0") int rawMaterialId,
                                 @RequestParam(required = false,defaultValue = "0") int auxiliaryId,
                                 @RequestParam(required = false,defaultValue = "0") int sparePartsId){
        if (sparePartsId!=0){
            if(sparePartsService.selectByDeviceTypeIdAnd(deviceTypeId,sparePartsId)==null){
               DeviceParePartsManage sparePartsConsumption= new DeviceParePartsManage();
               sparePartsConsumption.setDeviceTypeId(deviceTypeId);
               sparePartsConsumption.setSparePartsId((long) sparePartsId);
                sparePartsService.insert(sparePartsConsumption);
            }


        }
        return "ok";
    }

}
