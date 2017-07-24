package com.jey.mymvp.util;

import com.jey.mymvp.base.BasePresenter;
import com.jey.mymvp.base.BaseResponse;
import com.jey.mymvp.exception.ApiException;
import com.jey.mymvp.exception.ExceptionManager;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**rx工具类
 *
 * Created by jie on 2017/7/21.
 */

public class RxUtil {
    /**统一线程处理
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T,T> applySchedulers() { //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**统一处理结果
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseResponse<T>,T> handleResult() {
        return new Observable.Transformer<BaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponse<T>> baseResponseObservable) {
                return baseResponseObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> baseResponse) {
                        if ("200".equals(baseResponse.getResultCode())) {
                            return creatData(baseResponse.getResultBean());
                        }else {
                            return Observable.error(new ApiException(baseResponse.getResultCode(),baseResponse.getErrorMessage() == null ? baseResponse.getErrorStackTrack() : baseResponse.getErrorMessage()));
                        }
                    }
                }).onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    @Override
                    public Observable<? extends T> call(Throwable throwable) {
                        return Observable.error(ExceptionManager.handleException(throwable));
                    }
                });
            }
        };
    }

    private static <T> Observable<T> creatData(final T resultBean) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(resultBean);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(ExceptionManager.handleException(e));
                }
            }
        });
    }


}
