package com.study.benchmarkorm;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

public class ActiveApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Configuration dbConfiguration = new Configuration.Builder(this)
                .setDatabaseName("lib.db")
                .addModelClass(Person.class)
                .addModelClass(Book.class)
                .addModelClass(Library.class)
                .create();

        ActiveAndroid.initialize(dbConfiguration);
    }
}
