package com.study.benchmarkorm;

import android.app.Application;
import android.os.Environment;

import com.onurciner.OHibernateConfig;

public class BenchmarkApplication extends Application {

    public static final String DATABASE_EXTERNAL = Environment.getExternalStorageDirectory().getPath(); //external
    public static final String DATABASE_SUB = "/folderName/";
    public static final String DATABASE_DB_PATH = DATABASE_EXTERNAL + DATABASE_SUB;
    public static final String DATABASE_DATA_NAME = "databaseName.sqlite";

    @Override
    public void onCreate() {
        super.onCreate();
        OHibernateConfig.DB_PATH = DATABASE_DB_PATH; // DATABASE PATH
        OHibernateConfig.DB_NAME = DATABASE_DATA_NAME; // DATABASE NAME
    }

}
