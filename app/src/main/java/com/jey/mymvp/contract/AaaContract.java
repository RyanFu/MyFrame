package com.jey.mymvp.contract;

import com.jey.mymvp.base.BasePresenter;
import com.jey.mymvp.base.BaseView;
import com.jey.mymvp.model.AaaBean;

/**
 * Created by jie on 2017/7/21.
 */

public interface AaaContract {
    interface View extends BaseView {
        void getDataSuccess(AaaBean bean);
        void getDataError();
    }
    interface Presenter extends BasePresenter<View> {
        void getData(String date);
    }
}
