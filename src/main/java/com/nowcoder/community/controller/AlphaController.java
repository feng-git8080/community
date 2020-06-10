package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.uitl.CommouityUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("data")
    @ResponseBody
    public String selectALL() {
        return alphaService.selectAll();
    }

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello() {
        return "hello spring boot";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);

        }
        System.out.println(request.getParameter("code"));

        //返回响应的数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter Writer = response.getWriter();
        ) {
            Writer.write("<h1>一级标题</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get请求  获取数据，请求数据
    //students？current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "name student";
    }

    //student/123  @PathVariable 路径变量
    @RequestMapping(path = "student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String student(@PathVariable("id") int id) {
        System.out.println("id");
        return "123student";
    }

    //post 请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String savestudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //html数据,响应
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView teacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三三");
        modelAndView.addObject("age", "30");
        modelAndView.setViewName("/demo/teacher");
        return modelAndView;

    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String school(Model model) {
        model.addAttribute("name", "张三");
        model.addAttribute("age", "100");
        return "/demo/teacher";
    }
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> emp() {
         Map<String,Object> emp= new HashMap<>();
        emp.put("name","炸死");
        emp.put("age",99);
        emp.put("Salary",800000);
        return emp;
    }
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> emps() {
        List<Map<String,Object>> list=new ArrayList();
        Map<String,Object> emp1= new HashMap<>();
        emp1.put("name","炸死");
        emp1.put("age",99);
        emp1.put("Salary",800000);
        list.add(emp1);

        Map<String,Object> emp2= new HashMap<>();
        emp2.put("name","网文");
        emp2.put("age",9);
        emp2.put("Salary",10);
        list.add(emp2);

        emp2= new HashMap<>();
        emp2.put("name","zhaoliu");
        emp2.put("age",91);
        emp2.put("Salary",101);
        list.add(emp2);
        return list;
    }

    //cookie实例
    @RequestMapping(path="/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookis(HttpServletResponse response){
        //创建cookie
        Cookie cookie =new Cookie("code", CommouityUitl.getrateUUID());
        //cookis 生成的范围
        cookie.setPath("/community/alpha");
        //设置cookis的生存时间
        cookie.setMaxAge(60*10);
        //发送cookis
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path="/cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCook(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    //session 实例
    @RequestMapping(path="/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpSession session){
       session.setAttribute("id","112");
       session.setAttribute("name","test");
       return "set session";
    }
    @RequestMapping(path="/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
