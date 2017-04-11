package com.study.benchmarkorm.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.db.CursorWrappers.BookCursorWrapper;
import com.study.benchmarkorm.db.DatabaseHelper;
import com.study.benchmarkorm.db.LibraryDbSchema.*;
import com.study.benchmarkorm.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BookDao {

    private SQLiteDatabase mDatabase;

    public BookDao(SQLiteDatabase db) {
        mDatabase = db;
    }

    public List<Book> getAll() {
        List<Book> crimes = new ArrayList<>();
        BookCursorWrapper cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public void add(Book c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(BookTable.NAME, null, values);
    }
    public void delete(Book c) {
        String id = String.valueOf(c.getId());
        mDatabase.delete(BookTable.NAME,
                BookTable.Cols.ID + " = ?",
                new String[] {id});
    }

    public Book get(long id) {
        BookCursorWrapper cursor = query(
                BookTable.Cols.ID + " = ?",
        new String[] {String.valueOf(id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getBook();
        } finally {
            cursor.close();
        }
    }

    public void update(Book crime) {
        String id = String.valueOf(crime.getId());
        ContentValues values = getContentValues(crime);
        mDatabase.update(BookTable.NAME, values,
                BookTable.Cols.ID + " = ?",
                new String[] { id });
    }


    private static ContentValues getContentValues(Book crime) {
        ContentValues values = new ContentValues();
        values.put(BookTable.Cols.ID, crime.getId());
        values.put(BookTable.Cols.TITLE, crime.getTitle());
        values.put(BookTable.Cols.AUTHOR, crime.getAuthor());
        values.put(BookTable.Cols.PAGES_COUNT, crime.getPagesCount());
        values.put(BookTable.Cols.BOOK_ID, crime.getBookId());
        values.put(BookTable.Cols.LIBRARY_ID, crime.getLibrary().getId());
        return values;
    }

    private BookCursorWrapper query(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                BookTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new BookCursorWrapper(cursor);
    }
}
