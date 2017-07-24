package com.jey.mymvp.exception;

/**
 * 错误类型
 * Created by xyan on 2017/2/9.
 */

public enum ERROR {
    TOKEN_INVALID,//token 失效
    HTTP_ERROR , //HTTP 错误
    SERVER_ERROR ,//服务器错误
    PARSE_ERROR ,//解析错误
    UNKNOWN//未知错误
}
