package com.testography.androidmiddlegot.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.network.res.HouseModelRes;
import com.testography.androidmiddlegot.data.network.res.SwornMemberModelRes;
import com.testography.androidmiddlegot.data.storage.models.House;
import com.testography.androidmiddlegot.data.storage.models.HouseDao;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.data.storage.models.SwornMemberDao;
import com.testography.androidmiddlegot.utils.AppConfig;
import com.testography.androidmiddlegot.utils.ConstantsManager;
import com.testography.androidmiddlegot.utils.NetworkStatusChecker;
import com.testography.androidmiddlegot.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends BaseActivity {

    private DataManager mDataManager;

    private HouseDao mHouseDao;
    private SwornMemberDao mSwornMemberDao;

    private int numberOfSessions;
    private boolean mIsDelayOver;

    SharedPreferences mSharedPreferences;
    public static final String DB_AVAILABLE = "DB_AVAILABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        numberOfSessions = 0;
        mIsDelayOver = false;

        mDataManager = DataManager.getInstance();
        mHouseDao = mDataManager.getDaoSession().getHouseDao();
        mSwornMemberDao = mDataManager.getDaoSession().getSwornMemberDao();

        startPlannedDelay();

        mSharedPreferences = getSharedPreferences
                (DB_AVAILABLE, MODE_PRIVATE);
        String dbStatus = mSharedPreferences.getString(DB_AVAILABLE, null);

        if (dbStatus != null) {
            numberOfSessions++;
            launchMainActivity();
        } else {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(DB_AVAILABLE, "DB_AVAILABLE");
            editor.commit();

            loadHouse(ConstantsManager.houseOne);
            loadHouse(ConstantsManager.houseTwo);
            loadHouse(ConstantsManager.houseThree);
        }
    }

    private void loadHouse(int houseId) {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            Call<HouseModelRes> call = mDataManager.getHouseFromNetwork(houseId);
            numberOfSessions++;
            call.enqueue(new Callback<HouseModelRes>() {
                @Override
                public void onResponse(Call<HouseModelRes> call, Response<HouseModelRes> response) {
                    try {
                        if (response.code() == 200) {
                            HouseModelRes houseModelRes = response.body();

                            House house = new House(houseModelRes);
                            mHouseDao.insertOrReplace(house);

                            int houseId = Utils.getIdFromUri(houseModelRes.getId());
                            String words = houseModelRes.getWords();

                            fetchSwornMembers(getSwornMembersIdFromUri
                                    (houseModelRes.getSwornMembers()), houseId, words);

                            launchMainActivity();
                        }
                    } catch (NullPointerException e) {
                        Log.e("Retrofit error: ", e.toString());
                    }
                }

                @Override
                public void onFailure(Call<HouseModelRes> call, Throwable t) {
                    // TODO: Handle the failure
                }
            });
        }
    }

    private void fetchSwornMembers(final List<Integer> swornMembersId, final int
            houseId, final String words) {

        for (Integer id : swornMembersId) {
            Call<SwornMemberModelRes> call = mDataManager.getSwornMemberFromNetwork(id);
            numberOfSessions++;
            call.enqueue(new Callback<SwornMemberModelRes>() {
                @Override
                public void onResponse(Call<SwornMemberModelRes> call, Response<SwornMemberModelRes> response) {
                    try {
                        if (response.code() == 200) {
                            SwornMemberModelRes swornMemberModelRes = response.body();
                            SwornMember swornMember = new SwornMember
                                    (swornMemberModelRes, houseId, words);

                            mSwornMemberDao.insertOrReplace(swornMember);
                            launchMainActivity();
                        }
                    } catch (NullPointerException e) {
                        Log.e("Fetch SwornMember error", e.toString());
                    }
                }
                @Override
                public void onFailure(Call<SwornMemberModelRes> call, Throwable t) {
                    // TODO: Handle the error
                }
            });
        }
    }


    private List<Integer> getSwornMembersIdFromUri(List<String> swornMembersUri) {

        List<Integer> result = new ArrayList<>();

        for (String uri : swornMembersUri) {
            result.add(Utils.getIdFromUri(uri));
        }
        return result;
    }

    public void launchMainActivity() {
        numberOfSessions--;
        if (numberOfSessions != 0 || !mIsDelayOver) {
            return;
        }
        Intent intent = new Intent(SplashScreenActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startPlannedDelay() {
        new PlannedDelay().execute("");
    }

    private class PlannedDelay extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            numberOfSessions++;
            try {
                Thread.sleep(AppConfig.START_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mIsDelayOver = true;
            launchMainActivity();
        }
    }
}
