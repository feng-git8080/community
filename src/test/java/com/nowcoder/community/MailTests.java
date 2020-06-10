package com.nowcoder.community;

import com.nowcoder.community.uitl.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author ：Done
 * @date :Creat in 2020/4/19 0019
 * 20:29
 * @description ：邮箱测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes=CommunityApplication.class)
public class MailTests {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void mailTest(){

        mailClient.sendMail("fyj308091@163.com","test","testrool");
    }

    @Test
    public void testHtmlMail(){
        Context context=new Context();
        context.setVariable("username","sunde");
        /**
         * 模本文件在哪，数据在哪 发的网页
         */
        String content1=templateEngine.process("/mail/demo",context);
        System.out.println(content1);

        mailClient.sendMail("fyj308091@163.com","testhtml",content1);    }

    @Test
    public void testHtmlMail1() {
        Context context = new Context();
        context.setVariable("username", "sunday");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("fyj308091@163.com", "HTML", content);
    }
}

