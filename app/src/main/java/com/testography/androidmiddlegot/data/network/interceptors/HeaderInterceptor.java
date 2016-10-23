package com.testography.androidmiddlegot.data.network.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

//        PreferenceManager pm = DataManager.getInstance();

        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("User-Agent", "AndroidMiddleGoT")
                .header("Cache-Control", "max-age=" + (60 * 60 * 24));

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
