package com.study.benchmarkorm;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

import com.study.benchmarkorm.LibraryDbSchema.*;

public class SprinklesApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());

        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL("create table " + LibraryTable.NAME + "(" +
                        LibraryTable.Cols.ID + " integer primary key autoincrement, " +
                        LibraryTable.Cols.ADDRESS + ", " +
                        LibraryTable.Cols.NAME + ");");

                db.execSQL("create table " + BookTable.NAME + "(" +
                        BookTable.Cols.ID + " integer primary key autoincrement, " +
                        BookTable.Cols.TITLE + ", " +
                        BookTable.Cols.AUTHOR + ", " +
                        BookTable.Cols.PAGES_COUNT + ", " +
                        BookTable.Cols.BOOK_ID + ");");
                db.execSQL("create table " + PersonTable.NAME + "(" +
                        PersonTable.Cols.ID+" integer primary key autoincrement, " +
                        PersonTable.Cols.F_NAME + ", " +
                        PersonTable.Cols.S_NAME + ", " +
                        PersonTable.Cols.DATE + ", " +
                        PersonTable.Cols.GENDER + ", " +
                        PersonTable.Cols.PHONE + ", );");
            }
        });
    }
}
