package com.ljdo.service.impl;

import com.ljdo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单实现
 * @author fly
 * @create 2016-01-06 0:29
 */
@Service
public class OrderServiceImpl implements OrderService{

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private MailSender mailSender;
    @Resource
    private SimpleMailMessage templateMessage;

    private String msgTo  = "guidao418@gmail.com";

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    /**
     * 生成订单
     * 业务需求：生成订单，并将订单信息发送给客户
     */
    public void placeOrder() {

        //生成订单流程实现

        //通知客户，发送邮件
        //给模板信息和自定义内容创建一个线程安全的‘副本’
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(msgTo);
        msg.setText("这是使用spring-mail工具类发送邮件的哦!");
        System.out.print(templateMessage.getFrom());
        System.out.print(templateMessage.getSubject());
        try {
            this.mailSender.send(msg);
        } catch (MailException e) {
            log.error("邮件发送异常：" + e.getMessage());
        }

    }
}