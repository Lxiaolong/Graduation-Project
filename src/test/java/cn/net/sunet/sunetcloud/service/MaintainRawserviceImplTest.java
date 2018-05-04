package cn.net.sunet.sunetcloud.service;

import cn.net.sunet.sunetcloud.domain.MaintainRawMaterial;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/2
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintainRawserviceImplTest {
    @Autowired
    private MaintainRawserviceImpl maintainRawservice;
    @Test
    public void queryRA() throws Exception {
        System.out.println(new JSONGenerator().createJSONGenerator().setContent(maintainRawservice.queryRA()).asJson());
    }
    @Test
    public void queryPage() throws Exception {
        System.out.println(new JSONGenerator().createJSONGenerator().setContent(maintainRawservice.querypage(1,10))
                .asJson());
    }
    @Test
    public void insert(){
        MaintainRawMaterial maintainRawMaterial=new MaintainRawMaterial();
        maintainRawMaterial.setMain_material_name("电阻");
        maintainRawMaterial.setMain_material_price((float)50);
        maintainRawservice.insert(maintainRawMaterial);
    }
    @Test
    public void update(){
        MaintainRawMaterial maintainRawMaterial=new MaintainRawMaterial();
        maintainRawMaterial.setMain_material_id((long)100);
        maintainRawMaterial.setMain_material_price((float)60);
        maintainRawservice.update(maintainRawMaterial);
    }

}