package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ：Done
 * @date :Creat in 2020/3/25 0025
 * 21:33
 * @description ：
 */
@Service
@Scope("prototype")
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化");
    }

    @PostConstruct
    public  void inti(){
        System.out.println("出事化");
    }
   @PreDestroy
    public  void destoy(){
        System.out.println("销毁");
    }

    public String selectAll(){
       return alphaDao.seletAll();
    }
}
