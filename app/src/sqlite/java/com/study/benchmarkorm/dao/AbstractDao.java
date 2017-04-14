package com.study.benchmarkorm.dao;

import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import java.util.List;

public abstract class AbstractDao<T> {

    protected SQLiteDatabase mDatabase;
    protected SQLiteStatement insertStatement;
    protected SQLiteStatement updateStatement;

    public abstract List<T> getAll();
    public abstract List<T> get(int limit);
    public abstract void save(T item);
    public abstract void delete(T c);
    public abstract T get(long id);
    public abstract void update(T crime);
    protected abstract <K extends CursorWrapper> K query(String whereClause, String[] whereArgs);

    public void beginTransaction() {
        mDatabase.beginTransaction();
    }

    public void endTransaction() {
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }
}
