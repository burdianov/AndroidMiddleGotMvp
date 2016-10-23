package com.testography.androidmiddlegot.mvp.models;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;

public class SplashScreenModel {

    private DataManager mDataManager;
    private DaoSession mDaoSession;

    public SplashScreenModel() {
        mDataManager = DataManager.getInstance();

    }
}
