package com.testography.androidmiddlegot.mvp.views;

import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.mvp.presenters.IHouseFragmentPresenter;

import java.util.List;

public interface IHouseFragmentView {

    IHouseFragmentPresenter getPresenter();

    int getHouseNumber();

    void setupRecyclerView();

    void setupRecyclerViewAdapter(List<SwornMember> membersList);
}
