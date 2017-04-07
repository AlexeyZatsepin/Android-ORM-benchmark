package com.study.benchmarkorm;

import android.app.Application;

import com.onurciner.OHibernateConfig;

public class HibernateApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OHibernateConfig.DB_PATH = "/ohibernate/"; // DATABASE PATH
        OHibernateConfig.DB_NAME = "test"; // DATABASE NAME
    }

}
