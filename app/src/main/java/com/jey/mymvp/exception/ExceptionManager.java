package com.jey.mymvp.exception;

import com.google.gson.JsonParseException;
import com.jey.mymvp.util.ConstUtil;
import com.jey.mymvp.util.SystemUtil;
import com.jey.mymvp.util.ToastUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * 异常驱动器
 * Created by xyan on 2017/2/9.
 */

public class ExceptionManager {

    public static MyException handleException(Throwable e){
        MyException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new MyException( ERROR.HTTP_ERROR);
            ex.setErrorMsg("服务器连接异常");
            if (!SystemUtil.isNetworkConnected()){
                ex.setErrorMsg("请检查您的网络配置");
            }
        } else if (e instanceof ApiException){    //服务器返回的错误
            ex = new MyException(ERROR.SERVER_ERROR);
            if ("JB-".equals(((ApiException) e).getCode())||"JR-".equals(((ApiException) e).getCode()))
                ex.setErrorMsg("服务器异常");
            else
                ex.setErrorMsg(((ApiException) e).getMsg());
            switch (((ApiException) e).getCode()){
                case ConstUtil.ERROR_TOKEN_INVALID:
                    ex.setError(ERROR.TOKEN_INVALID);
                    //SharedPreferencesUtils.logout(); 退出登录
                    ex.setErrorMsg("系统检测到帐号登录异常，请重新登录");
                    break;
                default:
                    break;
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new MyException(ERROR.PARSE_ERROR);
            ex.setErrorMsg("数据处理异常");            //数据处理异常



        }else if(e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof UnknownHostException){
            ex = new MyException(ERROR.HTTP_ERROR);
            ex.setErrorMsg("连接超时");  //均视为网络错误
            if (!SystemUtil.isNetworkConnected()){
                ex.setErrorMsg("请检查您的网络配置");
            }
        }else {
            ex = new MyException(ERROR.UNKNOWN);
            ex.setErrorMsg("未知错误");          //未知错误

        }
       ToastUtil.shortShow(ex.getErrorMsg());
        return ex;
    }
}
