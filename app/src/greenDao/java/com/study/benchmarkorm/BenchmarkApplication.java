package com.study.benchmarkorm;

import android.app.Application;

import com.study.benchmarkorm.model.DaoMaster;
import com.study.benchmarkorm.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class BenchmarkApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "library2");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
