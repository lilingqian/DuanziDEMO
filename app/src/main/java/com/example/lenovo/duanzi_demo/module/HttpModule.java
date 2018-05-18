package com.example.lenovo.duanzi_demo.module;

import com.example.lenovo.duanzi_demo.net.Api;
import com.example.lenovo.duanzi_demo.net.DuanziApi;
import com.example.lenovo.duanzi_demo.net.DuanziApiService;
import com.example.lenovo.duanzi_demo.net.MyInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);
    }

    @Provides
    DuanziApi provideDuanziApi(OkHttpClient.Builder builder){

        builder.addInterceptor(new MyInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();


        DuanziApiService duanziApiService = retrofit.create(DuanziApiService.class);

        return DuanziApi.getDuanziApi(duanziApiService);

    }

}
