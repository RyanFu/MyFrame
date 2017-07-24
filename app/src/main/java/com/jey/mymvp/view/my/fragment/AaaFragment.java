package com.jey.mymvp.view.my.fragment;

import com.jey.mymvp.base.BaseFragment;
import com.jey.mymvp.contract.AaaContract;
import com.jey.mymvp.model.AaaBean;
import com.jey.mymvp.presenter.AaaPresenter;

/**
 * Created by jie on 2017/7/21.
 */

public class AaaFragment extends BaseFragment<AaaPresenter> implements AaaContract.View {
    @Override
    protected void initPresenter() {

    }

    @Override
    protected int initContentView() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void getDataSuccess(AaaBean bean) {

    }

    @Override
    public void getDataError() {

    }
}
