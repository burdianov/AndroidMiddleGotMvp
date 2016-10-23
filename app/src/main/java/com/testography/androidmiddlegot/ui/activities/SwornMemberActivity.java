package com.testography.androidmiddlegot.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.testography.androidmiddlegot.R;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.mvp.presenters.ISwornMemberPresenter;
import com.testography.androidmiddlegot.mvp.presenters.SwornMemberPresenter;
import com.testography.androidmiddlegot.mvp.views.ISwornMemberView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwornMemberActivity extends BaseActivity implements ISwornMemberView {

    private SwornMemberPresenter mSwornMemberPresenter = SwornMemberPresenter
            .getInstance();

    @BindView(R.id.words_content)
    TextView mWords;

    @BindView(R.id.born_content)
    TextView mBorn;

    @BindView(R.id.titles_content)
    TextView mTitles;

    @BindView(R.id.aliases_content)
    TextView mAliases;

    @BindView(R.id.father_name)
    TextView mFather;

    @BindView(R.id.mother_name)
    TextView mMother;

    private String mRemoteId;
    private String mDied;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sworn_member);
        ButterKnife.bind(this);

        mRemoteId = getIntent().getStringExtra("remoteId");

        mDied = "";

        mSwornMemberPresenter.takeView(this);
        mSwornMemberPresenter.initView();
    }

    //region ========== Interface Methods ==========

    @Override
    public void setupToolbar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setCollapsingToolbarLayout() {
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style
                .CollapsedAppBar);
    }

    @Override
    public String getRemoteId() {
        return mRemoteId;
    }

    @Override
    public void setContent(SwornMember swornMember) {
        mWords.setText(setInfo(swornMember.getWords()));
        mBorn.setText(setInfo(swornMember.getBorn()));
        mTitles.setText(setInfo(swornMember.getTitles()));
        mAliases.setText(setInfo(swornMember.getAliases()));

        mDied = swornMember.getDied().trim();

        if (mDied.length() != 0) {
            Snackbar.make(mCoordinatorLayout, "I died " + mDied, Snackbar
                    .LENGTH_LONG).show();
        }

        mFather.setText(setParentName(swornMember.getFather()));
        mMother.setText(setParentName(swornMember.getMother()));

        CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        toolbar.setTitle(swornMember.getName());
    }

    @Override
    public ISwornMemberPresenter getPresenter() {
        return mSwornMemberPresenter;
    }

    //endregion

    //region ========== Private Methods ==========

    private String setInfo(String infoFromDb) {
        if (infoFromDb.trim().length() == 0) {
            return "Info not provided";
        }
        return infoFromDb.trim();
    }

    private String setParentName(String infoFromDb) {
        if (infoFromDb.trim().length() == 0) {
            return "Unknown";
        }
        return infoFromDb.trim();
    }

    //endregion
}
