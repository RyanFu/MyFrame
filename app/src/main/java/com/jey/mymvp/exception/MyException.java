package com.jey.mymvp.exception;

/**
 * Created by jie on 2017/7/21.
 */

public class MyException extends Throwable {
    ERROR error;
    String errorMsg;

    public MyException(ERROR code){
        this.error=code;
    }

    public ERROR getError() {
        return error;
    }

    public void setError(ERROR error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
