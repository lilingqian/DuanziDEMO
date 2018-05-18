package com.example.lenovo.duanzi_demo.net;

import com.example.lenovo.duanzi_demo.bean.DuanziBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DuanziApiService  {


    @FormUrlEncoded
    @POST("quarter/getJokes")
    Observable<DuanziBean> getDuanzi(@Field("page") String page);

}
