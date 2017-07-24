package com.jey.mymvp.util;

import com.jey.mymvp.base.BaseApplication;

import java.io.File;

/**
 * 常量工具类
 * Created by xyan on 2017/1/17.
 */

public class ConstUtil {

    //================= PATH ====================
    public static final String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    //=================  服务器返回异常编码 =====================
    public static final String ERROR_TOKEN_INVALID="JB-user001";//token 失效

    //================== intent 参数 ================================
    public static  final String FROM_PAG="_frompage_";//主要用于页面返回刷新




}
