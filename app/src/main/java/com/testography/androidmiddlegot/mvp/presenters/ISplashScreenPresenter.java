package com.testography.androidmiddlegot.mvp.presenters;

import com.testography.androidmiddlegot.mvp.views.ISplashScreenView;

public interface ISplashScreenPresenter {

    void takeView(ISplashScreenView splashScreenView);

    void dropView();

    void initView();
}
