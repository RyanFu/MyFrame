package com.jey.mymvp.view.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jey.mymvp.R;
import com.jey.mymvp.base.BaseActivity;
import com.jey.mymvp.contract.MainContract;
import com.jey.mymvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }
}
