package com.ljdo.service.JavaMailSender.impl;

import com.ljdo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * JavaMailSender 实现方式
 * @author fly
 * @create 2016-01-09 17:33
 */
@Service
public class OrderServiceImpl implements OrderService{

    private  static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private JavaMailSender mailSender;

    public void placeOrder() {
        //生成订单流程实现

        //通知客户，发送邮件

        log.debug("进入JavaMailSender邮件发送:");
        //组装邮件对象
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress("ljdo.lj@gmail.com"));
                mimeMessage.setFrom(new InternetAddress("642795365@qq.com"));
                mimeMessage.setText("这是使用spring-mail工具类发送邮件的哦!");
            }
        };

        try {
            /*发送邮件*/
            this.mailSender.send(preparator);
        } catch (MailException e) {
            log.error("JavaMailSender邮件发送异常：" + e.getMessage());
        }
    }
}