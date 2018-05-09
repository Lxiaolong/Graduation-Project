package cn.net.sunet.sunetcloud.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/5/8
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void threadPoolTaskScheduler() throws Exception {
    }

    @Test
    public void testTime() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        Date date = dateFormat.parse("2018-05-09 08:01:00");
        ca.setTime(date);

        for (int i = 1; i < 10; i++) {
            Calendar calendar = Calendar.getInstance();
            Calendar cb = Calendar.getInstance();
            Date workTime = new Date();
            Date endTime = new Date();
            Date testTime = new Date();
            Date quality = new Date();
            int all = 0;
            calendar = (Calendar) ca.clone();
            testTime = calendar.getTime();
            mvc.perform(MockMvcRequestBuilders.post("/collection/deviceruntime/test_time").param("deviceId", String
                    .valueOf(3)
            ).param("testTime", dateFormat.format(testTime)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"));
            System.out.println(testTime);
            calendar.add(Calendar.MINUTE, 5 + (int) (Math.random() * 5));
            workTime = calendar.getTime();
            System.out.println(workTime);
            mvc.perform(MockMvcRequestBuilders.post("/collection/deviceruntime/work_time").param("deviceId", String
                    .valueOf(3)
            ).param("workTime", dateFormat.format(workTime)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"));
            cb = (Calendar) calendar.clone();
            calendar.add(Calendar.HOUR_OF_DAY, 11 + (int) (Math.random() * 2));
            calendar.add(Calendar.MINUTE, (int) (Math.random() * 30));
            cb.add(Calendar.HOUR_OF_DAY, 1);
            while (cb.before(calendar)) {
                quality = cb.getTime();
                int feed = (int) (450 + Math.random() * 50);
                int firstNg = (int) (20 + Math.random() * 20);
                int error = (int) (Math.random() * firstNg);
                int leakage = (int) (Math.random() * 10);
                int retest = (int)(( 0.3+(0.5*Math.random())) * feed+error+leakage);
                int throught = feed - firstNg + error - leakage;
                int ng = firstNg - error + leakage;
                mvc.perform(MockMvcRequestBuilders.post("/collection/devicequality")
                        .param("deviceId", String.valueOf(3))
                        .param("feed_number", String.valueOf(feed))
                        .param("discharge_number", String.valueOf(throught))
                        .param("ng_number", String.valueOf(ng))
                        .param("leakage_number", String.valueOf(leakage))
                        .param("retest_number", String.valueOf(retest))
                        .param("error_loading_number", String.valueOf(error))
                        .param("collection_time", dateFormat.format(cb.getTime().getTime()))
                )
                        //.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value(""));
                System.out.println(cb.getTime());
                System.out.println(feed + "-" + firstNg + "-" + error + "-" + leakage + "-" + retest + "-" + throught);
                cb.add(Calendar.HOUR_OF_DAY, 1);
                all += feed;

            }
            endTime = calendar.getTime();
            cb.add(Calendar.HOUR_OF_DAY, -1);
            float min = (float) (endTime.getTime() - cb.getTime().getTime()) / (1000 * 60 * 60);
            int feed = (int) ((int) (450 + Math.random() * 50) * min);
            int firstNg = (int) ((int) (20 + Math.random() * 20) * min);
            int error = (int) ((int) (Math.random() * firstNg) * min);
            int leakage = (int) ((int) (Math.random() * 10) * min);
            int retest = (int) ((0.3+(0.5*Math.random())) * feed+error+leakage);
            int throught = feed - firstNg + error - leakage;
            int ng = firstNg - error + leakage;
            System.out.println(feed + "-" + firstNg + "-" + error + "-" + leakage + "-" + retest + "-" + throught);
            mvc.perform(MockMvcRequestBuilders.post("/collection/devicequality")
                    .param("deviceId", String.valueOf(3))
                    .param("feed_number", String.valueOf(feed))
                    .param("discharge_number", String.valueOf(throught))
                    .param("ng_number", String.valueOf(ng))
                    .param("leakage_number", String.valueOf(leakage))
                    .param("retest_number", String.valueOf(retest))
                    .param("error_loading_number", String.valueOf(error))
                    .param("collection_time", dateFormat.format(cb.getTime().getTime()))
            )
                    .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value(""))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"));
            System.out.println(cb.getTime());
            all += feed;

            System.out.println(min);
            System.out.println(endTime);
            mvc.perform(MockMvcRequestBuilders.post("/collection/deviceruntime/down_time")
                    .param("deviceId", String.valueOf(3))
                    .param("downTime", dateFormat.format(endTime))
                    .param("runtimeOutput", String.valueOf(all)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"));
            ca.add(Calendar.HOUR_OF_DAY, 24);
        }
    }

    @Test
    public void workTime() throws Exception {
    }

    @Test
    public void downtime() throws Exception {
    }

    @Test
    public void maintain() throws Exception {
    }

    @Test
    public void accept() throws Exception {
    }

    @Test
    public void complete() throws Exception {
    }

    @Test
    public void devicequality() throws Exception {
    }

}