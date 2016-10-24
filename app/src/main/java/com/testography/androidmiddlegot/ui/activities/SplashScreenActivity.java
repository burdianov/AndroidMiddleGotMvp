package com.testography.androidmiddlegot.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.testography.androidmiddlegot.mvp.presenters.ISplashScreenPresenter;
import com.testography.androidmiddlegot.mvp.presenters.SplashScreenPresenter;
import com.testography.androidmiddlegot.mvp.views.ISplashScreenView;

public class SplashScreenActivity extends BaseActivity implements ISplashScreenView {

    private SplashScreenPresenter mSplashScreenPresenter = SplashScreenPresenter
            .getInstance();

//    private DataManager mDataManager;
//
//    private HouseDao mHouseDao;
//    private SwornMemberDao mSwornMemberDao;
//
//    private int numberOfSessions;
//    private boolean mIsDelayOver;
//
//    SharedPreferences mSharedPreferences;
//    public static final String DB_AVAILABLE = "DB_AVAILABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashScreenPresenter.takeView(this);
        mSplashScreenPresenter.initView();

//        numberOfSessions = 0;
//        mIsDelayOver = false;
//
//        mDataManager = DataManager.getInstance();
//        mHouseDao = mDataManager.getDaoSession().getHouseDao();
//        mSwornMemberDao = mDataManager.getDaoSession().getSwornMemberDao();
//
//        startPlannedDelay();
//
//        mSharedPreferences = getSharedPreferences
//                (DB_AVAILABLE, MODE_PRIVATE);
//        String dbStatus = mSharedPreferences.getString(DB_AVAILABLE, null);
//
//        if (dbStatus != null) {
//            numberOfSessions++;
//            launchMainActivity();
//        } else {
//            SharedPreferences.Editor editor = mSharedPreferences.edit();
//            editor.putString(DB_AVAILABLE, "DB_AVAILABLE");
//            editor.commit();
//
//            loadHouse(ConstantsManager.houseOne);
//            loadHouse(ConstantsManager.houseTwo);
//            loadHouse(ConstantsManager.houseThree);
//        }
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

//    private void loadHouse(int houseId) {
//        if (NetworkStatusChecker.isNetworkAvailable(this)) {
//            Call<HouseModelRes> call = mDataManager.getHouseFromNetwork(houseId);
//            numberOfSessions++;
//            call.enqueue(new Callback<HouseModelRes>() {
//                @Override
//                public void onResponse(Call<HouseModelRes> call, Response<HouseModelRes> response) {
//                    try {
//                        if (response.code() == 200) {
//                            HouseModelRes houseModelRes = response.body();
//
//                            House house = new House(houseModelRes);
//                            mHouseDao.insertOrReplace(house);
//
//                            int houseId = Utils.getIdFromUri(houseModelRes.getId());
//                            String words = houseModelRes.getWords();
//
//                            fetchSwornMembers(getSwornMembersIdFromUri
//                                    (houseModelRes.getSwornMembers()), houseId, words);
//
//                            launchMainActivity();
//                        }
//                    } catch (NullPointerException e) {
//                        Log.e("Retrofit error: ", e.toString());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<HouseModelRes> call, Throwable t) {
//                    // TODO: Handle the failure
//                }
//            });
//        }
//    }
//
//    private void fetchSwornMembers(final List<Integer> swornMembersId, final int
//            houseId, final String words) {
//
//        for (Integer id : swornMembersId) {
//            Call<SwornMemberModelRes> call = mDataManager.getSwornMemberFromNetwork(id);
//            numberOfSessions++;
//            call.enqueue(new Callback<SwornMemberModelRes>() {
//                @Override
//                public void onResponse(Call<SwornMemberModelRes> call, Response<SwornMemberModelRes> response) {
//                    try {
//                        if (response.code() == 200) {
//                            SwornMemberModelRes swornMemberModelRes = response.body();
//                            SwornMember swornMember = new SwornMember
//                                    (swornMemberModelRes, houseId, words);
//
//                            mSwornMemberDao.insertOrReplace(swornMember);
//                            launchMainActivity();
//                        }
//                    } catch (NullPointerException e) {
//                        Log.e("Fetch SwornMember error", e.toString());
//                    }
//                }
//                @Override
//                public void onFailure(Call<SwornMemberModelRes> call, Throwable t) {
//                    // TODO: Handle the error
//                }
//            });
//        }
//    }
//
//    private List<Integer> getSwornMembersIdFromUri(List<String> swornMembersUri) {
//
//        List<Integer> result = new ArrayList<>();
//
//        for (String uri : swornMembersUri) {
//            result.add(Utils.getIdFromUri(uri));
//        }
//        return result;
//    }
//
//    public void launchMainActivity() {
//        numberOfSessions--;
//        if (numberOfSessions != 0 || !mIsDelayOver) {
//            return;
//        }
//        Intent intent = new Intent(SplashScreenActivity.this,
//                MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void startPlannedDelay() {
//        new PlannedDelay().execute("");
//    }


//    private class PlannedDelay extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            numberOfSessions++;
//            try {
//                Thread.sleep(AppConfig.START_DELAY);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            mIsDelayOver = true;
//            launchMainActivity();
//        }
//    }
}
