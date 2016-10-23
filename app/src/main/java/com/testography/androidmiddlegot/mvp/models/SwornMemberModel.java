package com.testography.androidmiddlegot.mvp.models;

import com.testography.androidmiddlegot.data.managers.DataManager;

public class SwornMemberModel {

    private DataManager mDataManager;

    public SwornMemberModel() {
        mDataManager = DataManager.getInstance();
    }
}
