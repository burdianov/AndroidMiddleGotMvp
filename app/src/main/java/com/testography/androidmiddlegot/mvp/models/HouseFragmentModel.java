package com.testography.androidmiddlegot.mvp.models;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.data.storage.models.SwornMemberDao;

import java.util.ArrayList;
import java.util.List;

public class HouseFragmentModel {

    private DataManager mDataManager;
    private DaoSession mDaoSession;

    public HouseFragmentModel() {
        mDaoSession = DataManager.getInstance().getDaoSession();
        mDataManager = DataManager.getInstance();
    }

    public List<SwornMember> getSwornMembersInfo(int houseNumber) {
        return getSwornMembersFromDb(String
                .valueOf(houseNumber));
    }

    private List<SwornMember> getSwornMembersFromDb(String houseId) {
        List<SwornMember> membersList = new ArrayList<>();

        try {
            membersList = mDaoSession.queryBuilder(SwornMember.class)
                    .where(SwornMemberDao.Properties.HouseRemoteId.eq(houseId))
                    .orderDesc(SwornMemberDao.Properties.Name)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membersList;
    }
}
