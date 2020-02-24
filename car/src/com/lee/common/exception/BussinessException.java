package com.lee.common.exception;


import java.io.Serializable;

public class BussinessException extends  RuntimeException implements Serializable {


    private static final long serialVersionUID = 7604152134457889293L;

    private Integer errorCode;

    private String errorMsg;

    public BussinessException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("错误信息:");
        stringBuilder.append(getErrorMsg());
        stringBuilder.append("错误代码:");
        stringBuilder.append(getErrorCode());
        return  stringBuilder.toString();
    }
}
