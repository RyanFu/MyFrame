package com.jey.mymvp.base;

/**基础请求
 * Created by jie on 2017/7/21.
 */

public class BaseResponse<T> {
    private T resultBean;
    private String resultCode;
    private String errorMessage;
    private String errorStackTrack;

    public T getResultBean() {
        return resultBean;
    }

    public void setResultBean(T resultBean) {
        this.resultBean = resultBean;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStackTrack() {
        return errorStackTrack;
    }

    public void setErrorStackTrack(String errorStackTrack) {
        this.errorStackTrack = errorStackTrack;
    }
}
