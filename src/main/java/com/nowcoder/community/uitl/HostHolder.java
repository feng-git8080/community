package com.nowcoder.community.uitl;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author ：Done
 * @date :Creat in 2020/6/3 0003
 * 22:27
 * @description ：持有用户信息，用于代替session对象，长期持有
 */
@Component
public class HostHolder {
    private  ThreadLocal<User> users=new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }
    public User getUser(){
        return users.get();
    }
    public void clear(){
        users.remove();
    }
}
