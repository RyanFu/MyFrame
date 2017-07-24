package com.jey.mymvp.view.my.activity;

import com.jey.mymvp.R;
import com.jey.mymvp.base.BaseActivity;
import com.jey.mymvp.contract.AaaContract;
import com.jey.mymvp.model.AaaBean;
import com.jey.mymvp.presenter.AaaPresenter;

/**
 * Created by jie on 2017/7/21.
 */

public class AaaActivity extends BaseActivity<AaaPresenter> implements AaaContract.View {
    @Override
    protected void initPresenter() {
        mPresenter = new AaaPresenter();
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        //各种初始化

    }

    /**请求成功的界面部分处理
     * @param bean
     */
    @Override
    public void getDataSuccess(AaaBean bean) {

    }

    /**
     * 请求失败的界面部分处理
     */
    @Override
    public void getDataError() {

    }
}
