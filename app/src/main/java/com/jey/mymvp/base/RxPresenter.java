package com.jey.mymvp.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**基于Rx的Presenter封装，控制订阅生命周期
 * Created by jie on 2017/7/21.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }
}
