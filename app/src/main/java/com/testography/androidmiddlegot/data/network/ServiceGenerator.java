package com.testography.androidmiddlegot.data.network;

import com.testography.androidmiddlegot.data.network.interceptors.HeaderInterceptor;
import com.testography.androidmiddlegot.utils.AndroidMiddleGotMvpApplication;
import com.testography.androidmiddlegot.utils.AppConfig;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL_GOT)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(new HeaderInterceptor());
        httpClient.addInterceptor(logging);
        httpClient.cache(new Cache(AndroidMiddleGotMvpApplication.getContext()
                .getCacheDir(), Integer.MAX_VALUE));
//        httpClient.addNetworkInterceptor(new StethoInterceptor());

        Retrofit retrofit = sBuilder
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);
    }
}
