package com.jey.mymvp.http;

import com.jey.mymvp.base.BaseResponse;
import com.jey.mymvp.model.AaaBean;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

/**retrofit请求接口
 * Created by jie on 2017/7/21.
 */

public interface AaaApi {
    //Get方式
    @GET("uri")
    Observable<BaseResponse<AaaBean>> getDataByGet(@Query("date") String date);

    // Post方式
    @POST("uri")
    Observable<BaseResponse<AaaBean>> getDataByPost(@Body Map<String, Object> map);

    // Put方式
    @PUT("uri")
    Observable<BaseResponse<AaaBean>> getDataByPull(@Body Map<String, Object> map);
}
