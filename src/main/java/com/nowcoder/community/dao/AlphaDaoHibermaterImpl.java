package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author ：Done
 * @date :Creat in 2020/3/21 0021
 * 17:18
 * @description ：练习
 */

@Repository("alphaHibermate")
class AlphaDaoHibermaterImpl implements AlphaDao{

    @Override
    public String seletAll() {

        return"MyHibermate";
    }
}
