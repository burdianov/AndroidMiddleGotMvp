package com.testography.androidmiddlegot.mvp.presenters;

import android.content.Context;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.mvp.models.SwornMemberModel;
import com.testography.androidmiddlegot.mvp.views.ISwornMemberView;

import java.lang.ref.WeakReference;

public class SwornMemberPresenter implements ISwornMemberPresenter {

    private static Context sAppContext = DataManager.getInstance().getAppContext();
    private static SwornMemberPresenter sPresenterInstance = new SwornMemberPresenter();

    private SwornMemberModel mSwornMemberModel;
    private WeakReference<ISwornMemberView> mSwornMemberView;

    private SwornMember mSwornMember;

    private SwornMemberPresenter() {
        mSwornMemberModel = new SwornMemberModel();
    }

    public static SwornMemberPresenter getInstance() {
        return sPresenterInstance;
    }

    @Override
    public void takeView(ISwornMemberView swornMemberView) {
        mSwornMemberView = new WeakReference<ISwornMemberView>(swornMemberView);
        mSwornMember = mSwornMemberModel.getSwornMember(mSwornMemberView.get()
                .getRemoteId());
    }

    @Override
    public void dropView() {
        mSwornMemberView = null;
    }

    @Override
    public void initView() {
        mSwornMemberView.get().setupToolbar();
        mSwornMemberView.get().setCollapsingToolbarLayout();
        mSwornMemberView.get().setContent(mSwornMember);
    }

    @Override
    public SwornMember getSwornMember() {
        return null;
    }
}
