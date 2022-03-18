package com.tmall_backend.bysj.common.page;

import java.util.List;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-15
 */
@Data
public class PageBean<T> {
    private List<T> list;    //每页的数据
    private int pageNo;         //页序号
    private int pageSize;        //每页显示的条数
    private int totalNum;  //总的记录数
}
