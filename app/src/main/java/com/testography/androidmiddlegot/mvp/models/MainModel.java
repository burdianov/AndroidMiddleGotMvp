package com.testography.androidmiddlegot.mvp.models;

import com.testography.androidmiddlegot.data.managers.DataManager;

public class MainModel {

    private DataManager mDataManager;

    public MainModel() {
        mDataManager = DataManager.getInstance();
    }
}
