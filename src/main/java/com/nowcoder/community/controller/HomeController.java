package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Done
 * @date :Creat in 2020/3/30 0030
 * 23:36
 * @description ：
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //总行数，有多少条数据 方法调用前 Springmvc会自动话实例化 model，page而且将page注入Model
        page.setCount(discussPostService.FindDiscussPostCount(0));
        page.setPath("/index");

        List<DiscussPost> list=discussPostService.findDiscussPostAll(0,page.getoffset(),page.getLimit());
        List<Map<String,Object>> discussPosts= new ArrayList();
        if(list != null){
            for (DiscussPost post:list){
                Map<String,Object> map =new HashMap<>();
                map.put("post",post);
                User user =userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
