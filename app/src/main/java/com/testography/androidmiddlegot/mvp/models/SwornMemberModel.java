package com.testography.androidmiddlegot.mvp.models;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.data.storage.models.SwornMemberDao;

import java.util.ArrayList;
import java.util.List;

public class SwornMemberModel {

    private DataManager mDataManager;
    private DaoSession mDaoSession;

    public SwornMemberModel() {
        mDataManager = DataManager.getInstance();
        mDaoSession = DataManager.getInstance().getDaoSession();
    }

    public SwornMember getSwornMember(String remoteId) {
        return getSwornMemberFromDb(remoteId).get(0);
    }

    private List<SwornMember> getSwornMemberFromDb(String remoteId) {
        List<SwornMember> membersList = new ArrayList<>();

        try {
            membersList = mDaoSession.queryBuilder(SwornMember.class)
                    .where(SwornMemberDao.Properties.RemoteId.eq(remoteId))
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membersList;
    }
}
