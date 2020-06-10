package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：Done
 * @date :Creat in 2020/3/30 0030
 * 22:16
 * @description ：discussPostMapper
 */
@Mapper
public interface DiscussPostMapper {

    /**
     *
     * @param userId
     * @param offset 起始行的行号
     * @param limit  每页显示多少条
     * @return
     */
    List<DiscussPost>  selectDiscussPost(int userId, int offset, int limit);

    /**
     * 查询帖子数量
     * Param 注释用于取别名
     * 如果只有一个参数，并且在<if>里使用，必须加别名
     * @param userId
     * @return
     */
    int selectDiscussPostCount(@Param("userId")int userId);
}
