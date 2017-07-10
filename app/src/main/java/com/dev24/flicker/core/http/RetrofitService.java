package com.dev24.flicker.core.http;

import com.dev24.flicker.BuildConfig;
import com.dev24.flicker.utils.Sanitizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.EBean;

import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yuriy Aizenberg
 */

@EBean(scope = EBean.Scope.Singleton)
public class RetrofitService {

    private static final String ENDPOINT = "https://api.flickr.com/";
    private static final String KEY = "19b479459d31d241d503182ec6ff97bb";
    private static final String SECRES = "c64895fec397578b";

    @Getter
    private IService service;

    public RetrofitService() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .validateEagerly(true)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideHttpClient());
        Retrofit build = builder.build();
        service = build.create(IService.class);
    }

    private OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.BASIC);


        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    HttpUrl httpUrl = chain.request().url().newBuilder()
                            .addEncodedQueryParameter("format", "json")
                            .addEncodedQueryParameter("api_key", KEY)
                            .addEncodedQueryParameter("user_id", "95120925@N08")
                            .build();
                    Request authorization = chain.request().newBuilder().url(httpUrl).build();
                    Response proceed = chain.proceed(authorization);
                    Response sanitize = Sanitizer.sanitize(proceed);
                    return sanitize;
                })
                .followRedirects(false)
                .build();
    }
}
