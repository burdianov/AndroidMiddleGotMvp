package com.testography.androidmiddlegot.mvp.presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.mvp.models.SplashScreenModel;
import com.testography.androidmiddlegot.mvp.views.ISplashScreenView;
import com.testography.androidmiddlegot.utils.NetworkStatusChecker;

import java.lang.ref.WeakReference;

public class SplashScreenPresenter implements ISplashScreenPresenter {

    private static Context sAppContext = DataManager.getInstance().getAppContext();
    private static SplashScreenPresenter sPresenterInstance = new SplashScreenPresenter();

    private SplashScreenModel mSplashScreenModel;
    private WeakReference<ISplashScreenView> mSplashScreenView;

    SharedPreferences mSharedPreferences;
    public static final String DB_AVAILABLE = "DB_AVAILABLE";

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
        mSharedPreferences = sAppContext.getSharedPreferences
                (DB_AVAILABLE, sAppContext.MODE_PRIVATE);

        if (NetworkStatusChecker.isNetworkAvailable(sAppContext)) {
            mSplashScreenModel.processData(mSharedPreferences);
            mSplashScreenView.get().showLoad();
        }
    }

    @Override
    public void startActivity() {
        mSplashScreenView.get().hideLoad();
        mSplashScreenView.get().startMainActivityFromSplash();
    }
}
