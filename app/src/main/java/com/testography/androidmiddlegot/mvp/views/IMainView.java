package com.testography.androidmiddlegot.mvp.views;

import com.testography.androidmiddlegot.mvp.presenters.IMainPresenter;

public interface IMainView {

    IMainPresenter getPresenter();

    void setupToolbar();

    void setupViewPager();

    void setupDrawer();
}
