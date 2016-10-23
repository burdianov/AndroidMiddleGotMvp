package com.testography.androidmiddlegot.mvp.presenters;

import android.content.Context;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.mvp.models.MainModel;
import com.testography.androidmiddlegot.mvp.views.IMainView;

import java.lang.ref.WeakReference;

public class MainPresenter implements IMainPresenter {

    private static Context sAppContext = DataManager.getInstance().getAppContext();
    private static MainPresenter sPresenterInstance = new MainPresenter();

    private MainModel mMainModel;
    private WeakReference<IMainView> mMainView;

    private MainPresenter() {
        mMainModel = new MainModel();
    }

    public static MainPresenter getInstance() {
        return sPresenterInstance;
    }

    @Override
    public void takeView(IMainView mainView) {
        mMainView = new WeakReference<IMainView>(mainView);
    }

    @Override
    public void dropView() {
        mMainView = null;
    }

    @Override
    public void initView() {
        mMainView.get().setupToolbar();
        mMainView.get().setupViewPager();
        mMainView.get().setupDrawer();
    }
}
