package com.testography.androidmiddlegot.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.testography.androidmiddlegot.data.storage.models.DaoMaster;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class AndroidMiddleGotApplication extends Application {

    private static Context sContext;
    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,
                "androidmiddle-db");
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();

        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext() {
        return sContext;
    }

    public static DaoSession getDaoSession() {
        return sDaoSession;
    }
}
