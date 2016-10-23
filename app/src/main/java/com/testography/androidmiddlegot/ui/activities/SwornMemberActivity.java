package com.testography.androidmiddlegot.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.testography.androidmiddlegot.R;
import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.data.storage.models.SwornMemberDao;

import java.util.ArrayList;
import java.util.List;

public class SwornMemberActivity extends BaseActivity {

    private TextView mWords;
    private TextView mBorn;
    private TextView mTitles;
    private TextView mAliases;
    private TextView mFather;
    private TextView mMother;
    private String mRemoteId;
    private String mDied;

    private Toolbar mToolbar;
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sworn_member);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        mDaoSession = DataManager.getInstance().getDaoSession();

        mRemoteId = getIntent().getStringExtra("remoteId");

        mWords = (TextView) findViewById(R.id.words_content);
        mBorn = (TextView) findViewById(R.id.born_content);
        mTitles = (TextView) findViewById(R.id.titles_content);
        mAliases = (TextView) findViewById(R.id.aliases_content);
        mDied = "";

        mFather = (TextView) findViewById(R.id.father_name);
        mMother = (TextView) findViewById(R.id.mother_name);

        setContent();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar();
        setCollapsingToolbarLayout();
    }

    private void setCollapsingToolbarLayout() {
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style
                .CollapsedAppBar);
    }

    private List<SwornMember> getSwornMemberFromDb() {
        List<SwornMember> membersList = new ArrayList<>();

        try {
            membersList = mDaoSession.queryBuilder(SwornMember.class)
                    .where(SwornMemberDao.Properties.RemoteId.eq(mRemoteId))
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membersList;
    }

    private void setContent() {

        SwornMember swornMember;
        swornMember = getSwornMemberFromDb().get(0);

        if (swornMember != null) {
            mWords.setText(setInfo(swornMember.getWords().trim()));
            mBorn.setText(setInfo(swornMember.getBorn().trim()));
            mTitles.setText(setInfo(swornMember.getTitles().trim()));
            mAliases.setText(setInfo(swornMember.getAliases().trim()));

            mDied = swornMember.getDied().trim();
            if (mDied.length() != 0) {
                Snackbar.make(mCoordinatorLayout, "I died " + mDied, Snackbar
                        .LENGTH_LONG).show();
            }

            mFather.setText(setParentName(swornMember.getFather().trim()));
            mMother.setText(setParentName(swornMember.getMother().trim()));

            CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout)
                    findViewById(R.id.collapsing_toolbar);
            toolbar.setTitle(swornMember.getName());
        }
    }

    private String setInfo(String infoFromDb) {
        if (infoFromDb.length() == 0) {
            return "Info not provided";
        }
        return infoFromDb;
    }

    private String setParentName(String infoFromDb) {
        if (infoFromDb.length() == 0) {
            return "Unknown";
        }
        return infoFromDb;
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
