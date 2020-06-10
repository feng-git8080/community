package com.nowcoder.community.entity;

/**
 * @author ：Done
 * @date :Creat in 2020/5/6 0006
 * 21:36
 * @description ：CommunityConstant
 */
public interface CommunityConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS=0;
    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT=1;
    /**
     * 重复失败
     */
    int ACTIVATION_FALLURE=2;
    /*
    * 默认状态的登录凭证超时时间  12小时
    * */
    int DEFAULT_EXPIRED_SECONDS=3600*12;
    /*
    * 勾选记住我，记住状态下的登录凭证超时时间
    *
    * */
    int REMEMBER_EXPIRED_SECONDS=3600*24*100;

}
