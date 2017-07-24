package com.jey.mymvp.exception;

/**
 * API异常处理
 * Created by xyan on 2017/2/9.
 */

public class ApiException extends Exception {
    String code;
    String msg;

    public ApiException(String code, String msg){
        this.code=code;
        this.msg=msg;
    }
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
}
