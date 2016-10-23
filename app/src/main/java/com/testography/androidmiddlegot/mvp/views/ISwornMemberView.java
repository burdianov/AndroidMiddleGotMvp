package com.testography.androidmiddlegot.mvp.views;

import com.testography.androidmiddlegot.mvp.presenters.ISwornMemberPresenter;

public interface ISwornMemberView {
    ISwornMemberPresenter getPresenter();

    void setupToolbar();

    void setCollapsingToolbarLayout();

    void setContent();
}
