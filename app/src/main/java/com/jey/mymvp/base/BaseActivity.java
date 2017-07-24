package com.jey.mymvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/21.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;
    protected Activity mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());

        mUnbinder = ButterKnife.bind(this); //黄油刀绑定
        EventBus.getDefault().register(this);//注册事件传递
        mContext = this;

        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEventAndData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void btnEventBus(String message){
        // TODO
        //注册了eventbus,就要有对应处理方法,没有本方法报错
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();

    }

    /**
     * 展示加载
     */
    @Override
    public void showLoading() {

    }

    /**
     * 隐藏加载
     */
    @Override
    public void hideLoading() {

    }
    protected abstract void initPresenter();//初始化Presenter
    protected abstract int initContentView();//加载布局文件
    protected abstract void initEventAndData();//初始化数据，事件
}
