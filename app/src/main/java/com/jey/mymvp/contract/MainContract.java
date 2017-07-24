package com.jey.mymvp.contract;

import com.jey.mymvp.base.BasePresenter;
import com.jey.mymvp.base.BaseView;

/**
 * Created by jie on 2017/7/21.
 */

public interface MainContract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter<View> {

    }
}
