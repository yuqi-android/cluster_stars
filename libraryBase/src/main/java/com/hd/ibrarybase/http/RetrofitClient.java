package com.hd.ibrarybase.http;


import com.hd.ibrarybase.component.UserMgr;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/20
 **/
public class RetrofitClient {

    private static volatile RetrofitClient instance;

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    private OkHttpClient.Builder getHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor((chain -> {
                    Request request = chain.request().newBuilder()
                            .header("token", UserMgr.getInstance().getToken()).build();
                    return chain.proceed(request);
                }))
                .addInterceptor(logging);
        return httpClient;

    }

    public <S> S getApi(Class<S> sClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getHttpClient().build())
                .build();
        return retrofit.create(sClass);
    }

    public HttpsRequestApi getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getHttpClient().build())
                .build();
        return retrofit.create(HttpsRequestApi.class);
    }
}
