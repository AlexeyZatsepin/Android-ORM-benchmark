package com.study.benchmarkorm;

import fr.xebia.android.freezer.Freezer;

public class BenchmarkApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Freezer.onCreate(this);
    }

}
