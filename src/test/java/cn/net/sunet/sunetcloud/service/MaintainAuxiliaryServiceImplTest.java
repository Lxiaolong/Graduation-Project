package cn.net.sunet.sunetcloud.service;

import cn.net.sunet.sunetcloud.domain.MaintainAuxiliary;
import cn.net.sunet.sunetcloud.utils.JSONGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/3
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintainAuxiliaryServiceImplTest {
    @Autowired
    private MaintainAuxiliaryServiceImpl maintainAuxiliaryService;
    @Test
    public void queryPage() throws Exception {
        System.out.println(new JSONGenerator().createJSONGenerator().setContent(maintainAuxiliaryService.queryPage
                (1,10)).asJson());
    }
    @Test
    public void insert(){
        MaintainAuxiliary maintainRawMaterial=new MaintainAuxiliary();
        maintainRawMaterial.setOther_material_name("电阻");
        maintainRawMaterial.setOther_material_price((float)50);
        maintainAuxiliaryService.insert(maintainRawMaterial);
    }
    @Test
    public void update(){
        MaintainAuxiliary maintainRawMaterial=new MaintainAuxiliary();
        maintainRawMaterial.setOther_material_id((long)58);
        maintainRawMaterial.setOther_material_price((float)60);
        maintainAuxiliaryService.update(maintainRawMaterial);
    }

}