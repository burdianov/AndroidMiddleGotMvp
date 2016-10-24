package com.testography.androidmiddlegot.mvp.presenters;

import android.content.Context;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.mvp.models.HouseFragmentModel;
import com.testography.androidmiddlegot.mvp.views.IHouseFragmentView;

import java.lang.ref.WeakReference;
import java.util.List;

public class HouseFragmentPresenter implements IHouseFragmentPresenter {

    private static Context sAppContext = DataManager.getInstance().getAppContext();
    private static HouseFragmentPresenter sPresenterInstance = new HouseFragmentPresenter();

    private HouseFragmentModel mHouseFragmentModel;
    private WeakReference<IHouseFragmentView> mHouseFragmentView;

    private HouseFragmentPresenter() {
        mHouseFragmentModel = new HouseFragmentModel();
    }

    public static HouseFragmentPresenter getInstance() {
        return sPresenterInstance;
    }

    @Override
    public void takeView(IHouseFragmentView houseFragmentView) {
        mHouseFragmentView = new WeakReference<IHouseFragmentView>
                (houseFragmentView);
    }

    @Override
    public void dropView() {
        mHouseFragmentView = null;
    }

    @Override
    public void initView(int houseNumber) {
        mHouseFragmentView.get().setupRecyclerView();
        List<SwornMember> membersList = mHouseFragmentModel.getSwornMembersInfo
                (houseNumber);
        mHouseFragmentView.get().setupRecyclerViewAdapter(membersList);
    }
}
