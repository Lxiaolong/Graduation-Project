/*
package cn.net.sunet.sunetcloud.utils;


import com.itextpdf.text.DocumentException;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
*/
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/24
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 *//*


public class utilsTest {
    private HashMap<Integer,ScheduledFuture<?>> future=new HashMap<>();
    @Test
    public void createPdf() throws DocumentException, IOException {
        ThreadPoolTaskScheduler threadPoolTaskScheduler=new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.setPoolSize(10);
        Calendar now = Calendar.getInstance();
        int hour=(now.get(Calendar.HOUR_OF_DAY)+1)%24;
        now.add(Calendar.SECOND,+15);
        String cron=now.get(Calendar.SECOND)+" "+now.get(Calendar.MINUTE)+","+(now.get(Calendar.MINUTE)+1)+" "+now.get
                (Calendar
                .HOUR_OF_DAY)
                +"," +
                ""+hour+" "+now.get(Calendar.DAY_OF_MONTH)+" "+(now.get(Calendar.MONTH)+1)+" "+"?";
        System.out.println(cron);
        for (int i=1;i<5;i++) {
            future.put(i, threadPoolTaskScheduler.schedule(new MyRunnle(), new
                    CronTrigger(cron)));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        future.get(1).cancel(true);
        future.get(1).cancel(true);
        future.get(2).cancel(true);
        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
*/
