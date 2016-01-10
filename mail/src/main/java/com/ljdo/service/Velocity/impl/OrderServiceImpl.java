package com.ljdo.service.Velocity.impl;

import com.ljdo.User;
import com.ljdo.service.OrderService;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过veocity 创建邮件模板
 * @author fly
 * @create 2016-01-10 9:56
 */
@Service
public class OrderServiceImpl implements OrderService{

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private JavaMailSender mailSender;
    @Resource
    private VelocityEngine velocityEngine;
    public void placeOrder() {
        //生成订单流程实现

        log.debug("进入Velocity邮件发送:");

        //通知客户，发送邮件
        final User user = new User();
        user.setUserName("xxx");
        user.setEmailAddress("xxx@gmail.com");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                //如果内容中有中文，必须使用"GBK"编码
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"GBK");
                helper.setTo(user.getEmailAddress());//目的地
                helper.setFrom("xxxx@qq.com");//出发地

                Map model = new HashMap();
                model.put("user",user);
                //如果内容中有中文，必须使用"GBK"编码
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"mail-velocity.vm","GBK",model);
                log.info("邮件内容：【{}】",text);
                helper.setText(text,true);

                // 添加图片  window下绝对路径
                FileSystemResource resource = new FileSystemResource(new File("C:\\Users\\fly\\Desktop\\ef8b565974dddb67f5f14f984fd78960.jpg"));
                //以附件形式存在
                //helper.addAttachment("ef8b565974dddb67f5f14f984fd78960.jpg", resource);
                //以内容形式存在，主要作用于图片附件，ContentId用于在文中img指向，所以添加次序也有关系，先文本后资源，如果资源为非图片则无效
                helper.addInline("ef8b565974dddb67f5f14f984fd78960", resource);

                //如果有附件内容，则后加载标题。否则无效
                helper.setSubject("谢谢您的关注");//标题
            }
        };
        try {
            //发送邮件
            this.mailSender.send(preparator);
        } catch (MailException e) {
            log.error("MailSender邮件发送异常：" + e.getMessage());
        }
    }
}