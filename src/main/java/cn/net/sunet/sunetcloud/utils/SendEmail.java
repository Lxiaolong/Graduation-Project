package cn.net.sunet.sunetcloud.utils;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/23
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Lxiaolong
 */
public class SendEmail implements Runnable {

    private final JavaMailSender mailSender;
    private MaintainMalfunction maintainMalfunction;
    private Boolean flag=true;

    @Autowired
    public SendEmail(JavaMailSender mailSender, MaintainMalfunction maintainMalfunction) {
        this.mailSender = mailSender;
        this.maintainMalfunction = maintainMalfunction;
    }
    @Override
    public void run() {

        MimeMessage message = mailSender.createMimeMessage();
        System.out.println(new Date().toString());
        MimeMultipart multipart = new MimeMultipart();
        try {
            new CreatePdf().createPdf(maintainMalfunction);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MimeBodyPart  bodyPart = new MimeBodyPart();
        DataHandler dataHandler=new DataHandler(new FileDataSource("src/main/resources/pdf/"+"maintain"+maintainMalfunction.getId().toString()+".pdf"));
        try {MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
            messageHelper.setFrom("LXiaolongST@163.com");
            messageHelper.setSubject("主题：维修通知单");
            messageHelper.setText("详情见附件");
            if (flag) {
                messageHelper.setTo(maintainMalfunction.getMaintainEmail());
            }
            else {
                messageHelper.setTo(maintainMalfunction.getAccountEmail());
            }
            bodyPart.setDataHandler(dataHandler);
            bodyPart.setFileName(MimeUtility.encodeText(dataHandler.getName()));
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        flag=false;

    }
}
