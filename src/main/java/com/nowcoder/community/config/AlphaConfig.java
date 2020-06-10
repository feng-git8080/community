package com.nowcoder.community.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

/**
 * @author ：Done
 * @date :Creat in 2020/3/25 0025
 * 21:45
 * @description ：ss
 */
@Configurable
public class AlphaConfig {
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
    }

}
