package com.testography.androidmiddlegot.mvp.views;

import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.mvp.presenters.ISwornMemberPresenter;

public interface ISwornMemberView {
    ISwornMemberPresenter getPresenter();

    void setupToolbar();

    void setCollapsingToolbarLayout();

    void setContent(SwornMember swornMember);

    String getRemoteId();
}
