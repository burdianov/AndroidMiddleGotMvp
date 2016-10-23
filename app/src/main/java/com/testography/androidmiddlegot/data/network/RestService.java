package com.testography.androidmiddlegot.data.network;

import com.testography.androidmiddlegot.data.network.res.HouseModelRes;
import com.testography.androidmiddlegot.data.network.res.SwornMemberModelRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {
    @GET("houses/{id}")
    Call<HouseModelRes> getHouse(@Path("id") int id);

    @GET("characters/{id}")
    Call<SwornMemberModelRes> getSwornMember(@Path("id") int id);
}
