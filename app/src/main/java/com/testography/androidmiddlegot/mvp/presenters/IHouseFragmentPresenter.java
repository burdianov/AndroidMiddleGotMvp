package com.testography.androidmiddlegot.mvp.presenters;

import com.testography.androidmiddlegot.mvp.views.IHouseFragmentView;

public interface IHouseFragmentPresenter {

    void takeView(IHouseFragmentView houseFragmentView);

    void dropView();

    void initView(int houseNumber);
}
