package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：Done
 * @date :Creat in 2020/3/29 0029
 * 17:41
 * @description ：mapper
 */
@Mapper
public interface UserMapper {
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User selectByid(int id);
    /**
     * 根据username查询用户
     * @param username
     * @return
     */
    public User selectByName(String username);

    /**
     * 根据email查询用户
     * @param email
     * @return
     */
    public User selectByEmail(String email);

    /**
     * 增加数据
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     *
     * @param id
     * @param status   状态
     * @return
     */
    int updateUserStatus(int id,int status);

    /**
     * 修改头像
     * @param id
     * @param headerUrl  图片路径
     * @return
     */
    int updateUserHeader(int id,String headerUrl);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    int updateUserPassword(int id,String password);
}
