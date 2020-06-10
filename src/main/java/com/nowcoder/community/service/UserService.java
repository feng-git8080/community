package com.nowcoder.community.service;

import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.CommunityConstant;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.uitl.CommouityUitl;
import com.nowcoder.community.uitl.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ：Done
 * @date :Creat in 2020/3/30 0030
 * 23:24
 * @description ：
 */
@Service
public class UserService implements CommunityConstant {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    public User findUserById(int id){
        return userMapper.selectByid(id);
    }
    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public Map<String,Object> register(User user){
        Map<String,Object> map =new HashMap<>();
        //空值处理
        if(user==null){
            throw new IllegalArgumentException("参数不能为空");
        }
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }
        //验证账号
        User xu=userMapper.selectByName(user.getUsername());
        if(xu!=null){
            map.put("usernameMsg", "该用户已存在");

            return map;
        }
        //验证邮箱
        xu=userMapper.selectByEmail(user.getEmail());
        if(xu!=null){
            map.put("emailMsg", "邮箱已存在");
            return map;
        }
        //注册用户
        user.setSalt(CommouityUitl.getrateUUID().substring(0,5));
        user.setPassword(CommouityUitl.md5(user.getPassword()+user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommouityUitl.getrateUUID().substring(0,4));
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        //激活邮件

        Context context =new Context();
        context.setVariable("email",user.getEmail());
        //域名+项目名+激活的功能名(激活邮箱-activation)+用户名+激活码
        String url=domain+contextPath+"/activation/"+user.getId()+"/"+user.getActivationCode();
        context.setVariable("url",url);
        String content =templateEngine.process("mail/activation",context);
        mailClient.sendMail(user.getEmail(),"激活邮箱",content);
        return map;
    }

    public int actvation(int userId,String code){
        User user=userMapper.selectByid(userId);
        if(user.getStatus()==1){
             return ACTIVATION_REPEAT;
        }else if(user.getActivationCode().equals(code)){
            userMapper.updateUserStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else{
            return ACTIVATION_FALLURE;
        }
    }
    public Map<String,Object>  login(String username,String password,int expiredSecondes){
       Map<String,Object> map =new HashMap<String, Object>();
       //空值处理
        if(StringUtils.isBlank(username)){
            map.put("usernameMsg","账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("passwordMsg","密码不能为空");
            return map;
        }
        //验证账号
        User user=userMapper.selectByName(username);
        if( user==null){
            map.put("usernameMsg","改账号不存在");
            return  map;
        }
        if(user.getStatus()==0){
         map.put("usernameMsg","该账号未激活");
         return map;
        }
        //验证密码
        password=CommouityUitl.md5(password+user.getSalt());
        if(!user.getPassword().equals(password)){
            map.put("passwordMsg","密码不正确");
            return map;
        }
        //生成登录凭证
        LoginTicket loginTicket =new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommouityUitl.getrateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+expiredSecondes*1000));
        loginTicketMapper.insertLoginTicket(loginTicket);

        map.put("ticket",loginTicket.getTicket());

        return map;
    }
    public void logout(String ticket){
        loginTicketMapper.updateStatus(ticket,1);
    }

    public LoginTicket findLoginTicket(String ticket){
        return loginTicketMapper.selectByTicket(ticket);
    }

}
