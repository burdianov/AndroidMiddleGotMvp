package com.testography.androidmiddlegot.mvp.presenters;

import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.mvp.views.ISwornMemberView;

public interface ISwornMemberPresenter {

    void takeView(ISwornMemberView swornMemberView);

    void dropView();

    void initView();

    SwornMember getSwornMember();
}
