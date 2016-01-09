package com.ljdo.service.MimeMessageHelper.impl;

import com.ljdo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * MimeMessageHelper 方式
 * @author fly
 * @create 2016-01-09 20:32
 */
public class OrderServiceImpl implements OrderService{

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    public void placeOrder() {

        try {
            log.debug("MimeMessageHelper 方式发送邮件");
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost("smtp.qq.com");//主机
            sender.setPort(25);//端口号
            sender.setUsername("2507868527@qq.com");//用户名
            sender.setPassword("lijian418");//密码
            Properties properties = new Properties();
            properties.put("mail.transport.protocol","smtp");
            properties.put("mail.smtp.auth",true);
            properties.put("mail.smtp.starttls.enable",true);
            sender.setJavaMailProperties(properties);

            MimeMessage message = sender.createMimeMessage();
            // 你需要使用true作为标记来指出你多条信息所需要发送的内容,比如：附件、图片
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo("ljdo.lj@gmail.com");//目的地
            helper.setFrom("2507868527@qq.com");//出发地
            //helper.setText("Thank you for ordering!");//发送内容

            // 使用true作为标间指出文本包含的是HTML
            helper.setText("<html><body><div style='color:red;'>Thank you for ordering!</div><img src='cid:ef8b565974dddb67f5f14f984fd78960'></body></html>", true);

            helper.setSubject("xx发送");//添加标题

            // 添加图片  window下绝对路径
            FileSystemResource resource = new FileSystemResource(new File("C:\\Users\\fly\\Desktop\\ef8b565974dddb67f5f14f984fd78960.jpg"));
            //以附件形式存在
            //helper.addAttachment("ef8b565974dddb67f5f14f984fd78960.jpg", resource);
            //以内容形式存在，主要作用于图片附件，ContentId用于在文中img指向，所以添加次序也有关系，先文本后资源，如果资源为非图片则无效
            helper.addInline("ef8b565974dddb67f5f14f984fd78960", resource);



           //发送邮件
            sender.send(message);
        } catch (MessagingException e) {
            log.debug("MimeMessageHelper 方式发送邮件异常：{}", e.getMessage());
        }
    }

    public static void main(String[] args){
        new OrderServiceImpl().placeOrder();
    }
}