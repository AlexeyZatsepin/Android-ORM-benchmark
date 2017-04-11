package com.study.benchmarkorm.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.db.CursorWrappers.LibraryCursorWrapper;
import com.study.benchmarkorm.db.DatabaseHelper;
import com.study.benchmarkorm.db.LibraryDbSchema.LibraryTable;
import com.study.benchmarkorm.model.Library;

import java.util.ArrayList;
import java.util.List;


public class LibraryDao {

    private SQLiteDatabase mDatabase;

    public LibraryDao(SQLiteDatabase db) {
        mDatabase = db;
    }

    public List<Library> getAll() {
        List<Library> Librarys = new ArrayList<>();
        LibraryCursorWrapper cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Librarys.add(cursor.getLibrary());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Librarys;
    }

    public void add(Library c) {
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
        LibraryCursorWrapper cursor = query(
                LibraryTable.Cols.ID + " = ?",
        new String[] {String.valueOf(id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLibrary();
        } finally {
            cursor.close();
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

    private LibraryCursorWrapper query(String whereClause, String[] whereArgs) {
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
