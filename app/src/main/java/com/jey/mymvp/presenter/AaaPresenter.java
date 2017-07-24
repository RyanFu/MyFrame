package com.jey.mymvp.presenter;

import com.jey.mymvp.base.BaseResponse;
import com.jey.mymvp.base.RxPresenter;
import com.jey.mymvp.contract.AaaContract;
import com.jey.mymvp.http.AaaApi;
import com.jey.mymvp.http.RetrofitHelper;
import com.jey.mymvp.model.AaaBean;
import com.jey.mymvp.util.RxUtil;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by jie on 2017/7/21.
 */

public class AaaPresenter extends RxPresenter<AaaContract.View> implements AaaContract.Presenter {
    private static AaaApi mAaaApi = null;

    public AaaPresenter() {
        if (mAaaApi == null) {
            mAaaApi = (new RetrofitHelper()).getApiService(AaaApi.class);
        }
    }

    @Override
    public void getData(String date) {
        Subscription subscription = mAaaApi.getDataByGet(date)
                .compose(RxUtil.<BaseResponse<AaaBean>>applySchedulers())
                .compose(RxUtil.<AaaBean>handleResult())
                .subscribe(new Action1<AaaBean>() {
                               @Override
                               public void call(AaaBean aaaBean) {
                                   //处理请求成功逻辑数据部分

                                   //处理请求成功逻辑界面部分---交由对应Activity去处理   参数可以不是bean类,可是其它数据处理结果,在View借口修改
                                    mView.getDataSuccess(aaaBean);
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   //处理请求成功逻辑数据部分

                                   //处理失败逻辑---交由对应Activity去处理
                                    mView.getDataError();
                               }
                           }
                );
        addSubscription(subscription);
    }
}
