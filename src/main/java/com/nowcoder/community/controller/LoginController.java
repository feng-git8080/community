package com.nowcoder.community.controller;

import com.google.code.kaptcha.Producer;
import com.nowcoder.community.entity.CommunityConstant;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author ：Done
 * @date :Creat in 2020/4/20 0020
 * 21:00
 * @description ：登录控制层
 * 访问注册的页面
 */

@Controller
public class LoginController implements CommunityConstant {
    @Autowired
    private UserService userService;
    private  static final Logger logger= LoggerFactory.getLogger(LoginController.class);

    //获取样子吗kaptcha的bean
    @Autowired
    private Producer kaptchaProducer;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    /**
     * 获取注册页面
     * @return模板的路径，处理注册页面的请求
     */
    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterRage(){
        return "/site/register";
    }
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getloginRage(){
        return "/site/login";
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String,Object > map =userService.register(user);
        System.out.println(map.toString()+"11");
        if(map ==null || map.isEmpty()){
            model.addAttribute("msg","注册成功，已向你的邮件发送邮件，请激活");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            System.out.println(map.toString()+"222");
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return  "/site/register";
        }


    }
    //域名+项目名+激活的功能名(激活邮箱-activation)+用户名+激活码
    @RequestMapping(path = "/activation/{userId}/{code}",method=RequestMethod.GET )
    public String activation(Model model ,@PathVariable("userId") int userId,@PathVariable("code") String code){
        int result=userService.actvation(userId,code);
        if(result ==ACTIVATION_SUCCESS){
            model.addAttribute("msg","注册成功，账号亦可以正常使用");
            model.addAttribute("target","/login");
        }else if(result==ACTIVATION_REPEAT){
            model.addAttribute("msg","无效操作，重复操作");
            model.addAttribute("target","/index");
        }else{
            model.addAttribute("msg","激活码不正确");
            model.addAttribute("target","/index");
        }
        return  "/site/operate-result";
    }

    @RequestMapping(path="/kaptcha",method = RequestMethod.GET)
    public void getKaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        //生成验证码
       String text= kaptchaProducer.createText();
       BufferedImage image= kaptchaProducer.createImage(text);
       //4位数验证输入session
       session.setAttribute("kaptcha",text);
       //将图片输入到浏览器(png
        // 格式的图片)
        response.setContentType("image/png");
        try {
            OutputStream os=response.getOutputStream();
            //向游览器输出图片的工具   输出的图片，格式，用什么流输出
            ImageIO.write(image,"png",os);
        } catch (IOException e){
            logger.error("响应验证码失败"+ e.getMessage());
        }

    }

     @RequestMapping(path = "/login",method = RequestMethod.POST)
     public String Login(String username,String password, String code,
                         boolean rememberme,Model model,HttpSession session,
                         HttpServletResponse response){
        String kaptcha= (String) session.getAttribute("kaptcha");
        if(StringUtils.isBlank(kaptcha) || StringUtils.isBlank(code) || !kaptcha.equalsIgnoreCase(code)){
           model.addAttribute("codeMsg","验证码不正确");
           return "/site/login";
        }
        //检查账号，密码
      int expiredSends=rememberme?REMEMBER_EXPIRED_SECONDS:DEFAULT_EXPIRED_SECONDS;
        Map<String,Object> map =userService.login(username,password,expiredSends);
        if(map.containsKey("ticket")){
            Cookie cookie=new Cookie("ticket",map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSends);
            response.addCookie(cookie);
            return "redirect:/index";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
     }
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/login"; //默认gei请求
    }
}
