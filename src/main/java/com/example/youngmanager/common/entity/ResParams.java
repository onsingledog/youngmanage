package com.example.youngmanager.common.entity;

/**
 * Created by Administrator on 2018/6/6.
 */
public class ResParams {

    public ResParams(){

    }

    public ResParams(String code,String msg){
        this.code = code;
        this.msg = msg;
    }


    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
