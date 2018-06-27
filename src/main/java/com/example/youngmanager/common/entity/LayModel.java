package com.example.youngmanager.common.entity;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */
public class LayModel<T> {

    private int code;
    private String msg;
    private long count;
    private List<T> data = new ArrayList();

    public LayModel(){

    }


    public LayModel(int code, String msg, long count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public LayModel(PageInfo<T> pageInfo){
        this.code = 0;
        this.msg = "success";
        this.count = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
