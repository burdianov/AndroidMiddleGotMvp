package com.testography.androidmiddlegot.data.managers;

import com.testography.androidmiddlegot.data.network.RestService;
import com.testography.androidmiddlegot.data.network.ServiceGenerator;
import com.testography.androidmiddlegot.data.network.res.HouseModelRes;
import com.testography.androidmiddlegot.data.network.res.SwornMemberModelRes;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;
import com.testography.androidmiddlegot.utils.AndroidMiddleGotApplication;

import retrofit2.Call;

public class DataManager {
    private static DataManager INSTANCE = null;

    private RestService mRestService;
    private DaoSession mDaoSession;

    private DataManager() {
        mRestService = ServiceGenerator.createService(RestService.class);
        mDaoSession = AndroidMiddleGotApplication.getDaoSession();
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    //region ========== Network ==========

    public Call<HouseModelRes> getHouseFromNetwork(int id) {
        return mRestService.getHouse(id);
    }

    public Call<SwornMemberModelRes> getSwornMemberFromNetwork(int id) {
        return mRestService.getSwornMember(id);
    }

    //endregion

    //region ========== Database ==========

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    //endregion
}
