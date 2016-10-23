package com.testography.androidmiddlegot.mvp.presenters;

import android.content.Context;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.mvp.models.SplashScreenModel;
import com.testography.androidmiddlegot.mvp.views.ISplashScreenView;

import java.lang.ref.WeakReference;

public class SplashScreenPresenter implements ISplashScreenPresenter {

    private static Context sAppContext = DataManager.getInstance().getAppContext();
    private static SplashScreenPresenter sPresenterInstance = new SplashScreenPresenter();

    private SplashScreenModel mSplashScreenModel;
    private WeakReference<ISplashScreenView> mSplashScreenView;

    private SplashScreenPresenter() {
        mSplashScreenModel = new SplashScreenModel();
    }

    public static SplashScreenPresenter getInstance() {
        return sPresenterInstance;
    }

    @Override
    public void takeView(ISplashScreenView splashScreenView) {
        mSplashScreenView = new WeakReference<ISplashScreenView>(splashScreenView);
    }

    @Override
    public void dropView() {
        mSplashScreenView = null;
    }

    @Override
    public void initView() {

    }

}
