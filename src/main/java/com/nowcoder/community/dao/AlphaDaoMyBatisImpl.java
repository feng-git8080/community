package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author ：Done
 * @date :Creat in 2020/3/25 0025
 * 21:11
 * @description ：联系
 */
@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao{

    @Override
    public String seletAll() {
        return "MyBatis";
    }
}
