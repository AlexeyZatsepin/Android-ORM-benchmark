package com.study.benchmarkorm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.dao.BookDao;
import com.study.benchmarkorm.dao.LibraryDao;
import com.study.benchmarkorm.dao.PersonDao;

class DatabaseManager {
    private static DatabaseManager ourInstance;
    private SQLiteDatabase mDatabase;

    public static void init(Context context) {
        ourInstance = new DatabaseManager(context);
    }
    public static DatabaseManager get(){
        if (ourInstance==null){
            throw new ExceptionInInitializerError();
        }
        return ourInstance;
    }

    private DatabaseManager(Context context) {
        mDatabase = new DatabaseHelper(context.getApplicationContext())
                .getWritableDatabase();
    }

    public BookDao getBookDao(){
        return new BookDao(mDatabase);
    }

    public LibraryDao getLibraryDao(){
        return new LibraryDao(mDatabase);
    }
    public PersonDao getPersonDao(){
        return new PersonDao(mDatabase);
    }
}
