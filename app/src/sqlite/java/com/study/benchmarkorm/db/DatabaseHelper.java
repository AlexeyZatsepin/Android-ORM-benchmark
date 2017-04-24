package com.study.benchmarkorm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.benchmarkorm.db.LibraryDbSchema.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "library.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LibraryTable.NAME + "(" +
                LibraryTable.Cols.ID + " integer primary key autoincrement, " +
                LibraryTable.Cols.ADDRESS + ", " +
                LibraryTable.Cols.NAME + ");");

        db.execSQL("create table " + BookTable.NAME + "(" +
                BookTable.Cols.ID + " integer primary key autoincrement, " +
                BookTable.Cols.TITLE + ", " +
                BookTable.Cols.AUTHOR + ", " +
                BookTable.Cols.PAGES_COUNT + ", " +
                BookTable.Cols.BOOK_ID + ", " +
                BookTable.Cols.LIBRARY_ID + " integer,"
                + " FOREIGN KEY (" + BookTable.Cols.LIBRARY_ID + ") REFERENCES " + LibraryTable.NAME + "(" + LibraryTable.Cols.ID + ") ON DELETE CASCADE);");
        db.execSQL("create table " + PersonTable.NAME + "(" +
                PersonTable.Cols.ID+" integer primary key autoincrement, " +
                PersonTable.Cols.F_NAME + ", " +
                PersonTable.Cols.S_NAME + ", " +
                PersonTable.Cols.DATE + ", " +
                PersonTable.Cols.GENDER + ", " +
                PersonTable.Cols.PHONE + ", " +
                PersonTable.Cols.LIBRARY_ID + " integer,"
                + " FOREIGN KEY (" + PersonTable.Cols.LIBRARY_ID + ") REFERENCES " + LibraryTable.NAME + "(" + LibraryTable.Cols.ID + ") ON DELETE CASCADE);");

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + BookTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PersonTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LibraryTable.NAME);

        // Create tables again
        onCreate(db);
    }
}
