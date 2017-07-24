package com.jey.mymvp.base;

/**
 * Created by jie on 2017/7/21.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
