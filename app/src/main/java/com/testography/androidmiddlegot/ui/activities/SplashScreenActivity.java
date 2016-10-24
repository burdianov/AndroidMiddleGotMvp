package com.testography.androidmiddlegot.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.testography.androidmiddlegot.R;
import com.testography.androidmiddlegot.mvp.presenters.ISplashScreenPresenter;
import com.testography.androidmiddlegot.mvp.presenters.SplashScreenPresenter;
import com.testography.androidmiddlegot.mvp.views.ISplashScreenView;

public class SplashScreenActivity extends BaseActivity implements ISplashScreenView {

    private SplashScreenPresenter mSplashScreenPresenter = SplashScreenPresenter
            .getInstance();

    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashScreenPresenter.takeView(this);
        mSplashScreenPresenter.initView();
    }

    @Override
    public ISplashScreenPresenter getPresenter() {
        return mSplashScreenPresenter;
    }

    @Override
    public void startMainActivityFromSplash() {
        Intent intent = new Intent(SplashScreenActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoad() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.custom_dialog);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable
                    (Color.TRANSPARENT));
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        } else {
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        }
    }

    @Override
    public void hideLoad() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.hide();
            }
        }
    }
}
