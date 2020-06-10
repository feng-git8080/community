package com.nowcoder.community.entity;

import java.security.PrivateKey;

/**
 * @author ：Done
 * @date :Creat in 2020/3/31 0031
 * 23:04
 * @description ：封装分页相关的信息
 */
public class Page {
    //当前第几页
    private int current=1;
    //每项显示多少条
    private int limit=10;
    //一共多少条数据，用于计算总页数
    private int count;
    //查询路径(复用分页路径)
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {

        if(current >0){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>0 && limit<100){
            this.limit = limit;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(count>=0){
            this.count = count;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     * @return
     */
    public int getoffset(){
        //current*limit-limit
        return (current-1)*limit;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotal(){
        //count/limit(+1)
        if(count % limit ==0){
            return count/limit;
        }else{
            return count/limit+1;
        }
    }

    /**
     * 获取起始页码
     * @return
     */
    public int getFrom(){
        int from =current-2;
        return from< 1 ? 1 :from;
    }

    /**
     * 获取结束页码
     * @return
     */
    public int getTo(){
      int to=current+2;
      int total=getTotal();
      return  to > total ? total:to;
    }
}
