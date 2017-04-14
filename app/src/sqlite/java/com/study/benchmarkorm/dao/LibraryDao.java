package com.study.benchmarkorm.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.db.CursorWrappers;
import com.study.benchmarkorm.db.CursorWrappers.LibraryCursorWrapper;
import com.study.benchmarkorm.db.LibraryDbSchema;
import com.study.benchmarkorm.db.LibraryDbSchema.LibraryTable;
import com.study.benchmarkorm.model.Library;

import java.util.ArrayList;
import java.util.List;


public class LibraryDao extends AbstractDao<Library>{

    public LibraryDao(SQLiteDatabase db) {
        mDatabase = db;
    }

    public List<Library> getAll() {
        List<Library> libraries = new ArrayList<>();
        try (LibraryCursorWrapper cursor = query(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                libraries.add(cursor.getLibrary());
                cursor.moveToNext();
            }
        }
        return libraries;
    }
    public List<Library> get(int limit) {
        mDatabase.beginTransaction();
        List<Library> result = new ArrayList<>();
        Cursor cursor = mDatabase.query(LibraryDbSchema.LibraryTable.NAME,null,null,null,null,null,null,String.valueOf(limit));
        CursorWrappers.LibraryCursorWrapper libCursorWrapper = new CursorWrappers.LibraryCursorWrapper(cursor);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(libCursorWrapper.getLibrary());
        }
        mDatabase.setTransactionSuccessful();
        cursor.close();
        mDatabase.endTransaction();
        return result;
    }

    public void save(Library c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(LibraryTable.NAME, null, values);
    }
    public void delete(Library c) {
        String id = String.valueOf(c.getId());
        mDatabase.delete(LibraryTable.NAME,
                LibraryTable.Cols.ID + " = ?",
                new String[] {id});
    }

    public Library get(long id) {
        mDatabase.beginTransaction();
        try (LibraryCursorWrapper cursor = query(
                LibraryTable.Cols.ID + " = ?",
                new String[]{String.valueOf(id)})) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            return cursor.getLibrary();
        }
    }

    public void update(Library Library) {
        String id = String.valueOf(Library.getId());
        ContentValues values = getContentValues(Library);
        mDatabase.update(LibraryTable.NAME, values,
                LibraryTable.Cols.ID + " = ?",
                new String[] { id });
    }


    private static ContentValues getContentValues(Library Library) {
        ContentValues values = new ContentValues();
        values.put(LibraryTable.Cols.ID, Library.getId());
        values.put(LibraryTable.Cols.NAME, Library.getName());
        values.put(LibraryTable.Cols.ADDRESS, Library.getAddress());
        return values;
    }

    protected LibraryCursorWrapper query(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                LibraryTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new LibraryCursorWrapper(cursor);
    }

}
