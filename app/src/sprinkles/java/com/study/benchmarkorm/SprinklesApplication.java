package com.study.benchmarkorm;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

public class SprinklesApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());

        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE Book ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "author TEXT, title TEXT, pagesCount INTEGER, bookID INTEGER)");
            }
        });
    }
}
