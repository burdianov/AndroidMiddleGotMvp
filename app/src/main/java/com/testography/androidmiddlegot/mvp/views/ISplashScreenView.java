package com.testography.androidmiddlegot.mvp.views;

import com.testography.androidmiddlegot.mvp.presenters.ISplashScreenPresenter;

public interface ISplashScreenView {
    ISplashScreenPresenter getPresenter();

    void startMainActivityFromSplash();

    void showLoad();

    void hideLoad();
}
