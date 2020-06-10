package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Done
 * @date :Creat in 2020/3/30 0030
 * 23:13
 * @description ：DiscussPostService
 */
@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

     public List<DiscussPost>  findDiscussPostAll(int userId, int offset, int limit){
         return discussPostMapper.selectDiscussPost( userId,  offset,  limit);
     }

     public int FindDiscussPostCount(int userId){
         return discussPostMapper.selectDiscussPostCount(userId);
     }
}
