package com.testography.androidmiddlegot.mvp.presenters;

import com.testography.androidmiddlegot.mvp.views.IMainView;

public interface IMainPresenter {

    void takeView(IMainView mainView);

    void dropView();

    void initView();
}
