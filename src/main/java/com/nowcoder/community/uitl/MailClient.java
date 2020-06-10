package com.nowcoder.community.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author ：Done
 * @date :Creat in 2020/4/19 0019
 * 15:21
 * @description ：邮箱客户端  委托新浪去做
 */
@Component
public class MailClient {
    /**
     * 记录日志
     */
    private static  final Logger logger= LoggerFactory.getLogger(MailClient.class);
    /**
     * 核心接口
     */
    @Autowired
   private JavaMailSender mailSender;
    /**
     * 发送人：服务器
     */
    @Value("${spring.mail.username}")
    private  String from;

    /**
     *
     * @param to 发送给谁
     * @param subject 发送标题
     * @param content  发送内容
     */
    public void sendMail(String to,String subject , String content){

        try {
            MimeMessage message =mailSender.createMimeMessage();
            MimeMessageHelper helper =new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败"+e.getMessage());
        }
    }
}
