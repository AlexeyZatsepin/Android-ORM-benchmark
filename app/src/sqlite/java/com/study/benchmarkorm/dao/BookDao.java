package com.study.benchmarkorm.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.db.CursorWrappers.BookCursorWrapper;
import com.study.benchmarkorm.db.LibraryDbSchema.BookTable;
import com.study.benchmarkorm.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BookDao extends AbstractDao<Book> {

    public BookDao(SQLiteDatabase db) {
        mDatabase = db;
        String insertSQL = "INSERT INTO "+ BookTable.NAME +" ("+
//                BookTable.Cols.ID+", "+
                BookTable.Cols.TITLE + ", " +
                BookTable.Cols.AUTHOR + ", " +
                BookTable.Cols.PAGES_COUNT + ", " +
                BookTable.Cols.BOOK_ID + ", " +
                BookTable.Cols.LIBRARY_ID +") VALUES (?, ?, ?, ?, ?)";
        insertStatement = mDatabase.compileStatement(insertSQL);
        String updateSQL = "UPDATE "+ BookTable.NAME +
                " SET "+BookTable.Cols.AUTHOR+"=? WHERE "+BookTable.Cols.ID+"=?";
        updateStatement = mDatabase.compileStatement(updateSQL);
        String count = "SELECT COUNT(*) FROM " + BookTable.NAME;
        selectStatement = mDatabase.compileStatement(count);
    }

    public List<Book> getAll() {
        List<Book> crimes = new ArrayList<>();
        try (BookCursorWrapper cursor = query(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getBook());
                cursor.moveToNext();
            }
        }
        return crimes;
    }

    public List<Book> get(int limit) {
        List<Book> result = new ArrayList<>();
        Cursor cursor = mDatabase.query(BookTable.NAME,null,null,null,null,null,null,String.valueOf(limit));
        BookCursorWrapper bookCursorWrapper = new BookCursorWrapper(cursor);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(bookCursorWrapper.getBook());
        }
        cursor.close();
        return result;
    }

    public void save(Book c) {
        insertStatement.clearBindings();
        insertStatement.bindString(1,c.getTitle());
        insertStatement.bindString(2,c.getAuthor());
        insertStatement.bindLong(3,c.getPagesCount());
        insertStatement.bindLong(4,c.getBookId());
        if (c.getLibrary()!=null){
            insertStatement.bindLong(5,c.getLibrary().getId());
        }else{
            insertStatement.bindNull(5);
        }
        insertStatement.executeInsert();
    }
    public void delete(Book c) {
        String id = String.valueOf(c.getId());
        mDatabase.delete(BookTable.NAME,
                BookTable.Cols.ID + " = ?",
                new String[] {id});
    }

    public Book get(long id) {
        try (BookCursorWrapper cursor = query(
                BookTable.Cols.ID + " = ?",
                new String[]{String.valueOf(id)})) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getBook();
        }
    }

    public void update(Book book) {
        updateStatement.clearBindings();
        updateStatement.bindString(1,book.getAuthor());
        updateStatement.bindLong(2,book.getId());
        updateStatement.executeUpdateDelete();
    }

    protected BookCursorWrapper query(String whereClause, String[] whereArgs) {
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
