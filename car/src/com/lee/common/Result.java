package com.lee.common;

public class Result {


    private  Integer code;
    private  String msg;
    private  Object data;

    public Result() {
        this.code =CodeMsg.SUCCESS.code;
        this.msg=CodeMsg.SUCCESS.msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Object data) {
        this();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}