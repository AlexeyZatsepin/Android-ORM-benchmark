package com.study.benchmarkorm;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;


public class BenchmarkApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    public static class HelperFactory{

        private static DatabaseHelper databaseHelper;

        public static DatabaseHelper getHelper(){
            return databaseHelper;
        }
        public static void setHelper(Context context){
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }

        public static void releaseHelper(){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }


}
