package com.nowcoder.community.uitl;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Done
 * @date :Creat in 2020/6/2 0002
 * 22:07
 * @description ：Cookie工具类
 */
public class CookieUtil {

    public static  String getValue(HttpServletRequest request, String name){
        if(request ==null || name==null ){
            throw new IllegalArgumentException("scookie中，参数为空");
        }
        Cookie [] cookies =request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies){
                if(cookie.getName().equals(name)){
                    return  cookie.getValue();
                }
            }
        }
             return  null;
    }
}
