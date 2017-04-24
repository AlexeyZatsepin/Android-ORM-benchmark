package com.study.benchmarkorm.dao;

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
        String insertSQL = "INSERT INTO "+ LibraryTable.NAME +" ("+
                LibraryTable.Cols.ADDRESS + ", " +
                LibraryTable.Cols.NAME + ") VALUES (?, ?)";
        insertStatement = mDatabase.compileStatement(insertSQL);
        String updateSQL = "UPDATE "+ LibraryTable.NAME +
                " SET "+ LibraryTable.Cols.NAME+"=? WHERE "+ LibraryTable.Cols.ID+"=?";
        updateStatement = mDatabase.compileStatement(updateSQL);
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
        insertStatement.clearBindings();
        insertStatement.bindString(1,c.getAddress());
        insertStatement.bindString(2,c.getName());
        insertStatement.executeInsert();
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

    public void update(Library library) {
        updateStatement.clearBindings();
        updateStatement.bindString(1,library.getName());
        updateStatement.bindLong(2,library.getId());
        updateStatement.executeUpdateDelete();
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
