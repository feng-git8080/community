package com.nowcoder.community.uitl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author ：Done
 * @date :Creat in 2020/4/20 0020
 * 23:09
 * @description ：注册
 */
public class CommouityUitl {
    //生成随机字符串
    public static  String getrateUUID(){

        return UUID.randomUUID().toString().replaceAll("-","");
    }
    //MD5加密
    /**
     *
     * @param key 随机字符串
     * @return
     */
    public static  String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
