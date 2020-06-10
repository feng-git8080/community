package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author ：Done
 * @date :Creat in 2020/3/30 0030
 * 20:30
 * @description ：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes=CommunityApplication.class)
public class MapperTest {

    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectID(){
        User user=userMapper.selectByid(101);
        System.out.println(user);

        user=userMapper.selectByName("liubei");
        System.out.println(user);

        user=userMapper.selectByEmail("nowcoder102@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user =new User();
        user.setUsername("张三");
        user.setUsername("123456");
        user.setSalt("abc");
        user.setEmail("123@123.com");
        user.setHeaderUrl("http://www.nowcoder.com/111.png");
        user.setCreateTime(new Date());
        int rows=userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdataUser(){
        int rouws=userMapper.updateUserStatus(150,1);
        System.out.println(rouws);

        rouws=userMapper.updateUserPassword(150,"987654");
        System.out.println(rouws);

        rouws=userMapper.updateUserHeader(150,"http://www.nowcoder.com/199.png");
        System.out.println(rouws);
    }

    @Test
    public void testDiscussPost(){
       List<DiscussPost> list =discussPostMapper.selectDiscussPost(149,0,10);
          for (DiscussPost post:list){
              System.out.println(post);
          }
          int count =discussPostMapper.selectDiscussPostCount(149);
        System.out.println(count);
    }
    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket =new LoginTicket();
        loginTicket.setId(11);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void testSelectTicket(){
        LoginTicket loginTicket=loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus("abc",1);
         loginTicket=loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

    }
}
