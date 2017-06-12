package com.study.benchmarkorm.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.db.CursorWrappers.LibraryCursorWrapper;
import com.study.benchmarkorm.db.CursorWrappers.PersonCursorWrapper;
import com.study.benchmarkorm.db.LibraryDbSchema;
import com.study.benchmarkorm.db.LibraryDbSchema.PersonTable;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.List;


public class PersonDao extends AbstractDao<Person> {

    public PersonDao(SQLiteDatabase db) {
        mDatabase = db;
        String insertSQL = "INSERT INTO " + PersonTable.NAME + " (" +
                PersonTable.Cols.F_NAME + ", " +
                PersonTable.Cols.S_NAME + ", " +
                PersonTable.Cols.DATE + ", " +
                PersonTable.Cols.GENDER + ", " +
                PersonTable.Cols.PHONE + ", " +
                PersonTable.Cols.LIBRARY_ID + ") VALUES (?, ?, ?, ?, ?, ?)";
        insertStatement = mDatabase.compileStatement(insertSQL);
        String updateSQL = "UPDATE " + PersonTable.NAME +
                " SET " + PersonTable.Cols.F_NAME + "=?, " + PersonTable.Cols.S_NAME + "=?  WHERE " + PersonTable.Cols.ID + "=?";
        updateStatement = mDatabase.compileStatement(updateSQL);
        String count = "SELECT COUNT(*) FROM " + PersonTable.NAME;
        selectStatement = mDatabase.compileStatement(count);
    }

    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        try (PersonCursorWrapper cursor = query(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                persons.add(cursor.getPerson());
                cursor.moveToNext();
            }
        }
        return persons;
    }

    public List<Person> get(int limit) {
        List<Person> result = new ArrayList<>();
        Cursor cursor = mDatabase.query(LibraryDbSchema.PersonTable.NAME, null, null, null, null, null, null, String.valueOf(limit));
        PersonCursorWrapper personCursorWrapper = new PersonCursorWrapper(cursor);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Person person = personCursorWrapper.getPerson();
            result.add(person);
            if (Library.map.get(person.getLibraryId())==null){
                LibraryCursorWrapper libraryCursor = new LibraryCursorWrapper(mDatabase.rawQuery("SELECT * FROM "+ LibraryDbSchema.LibraryTable.NAME+ " where "+ LibraryDbSchema.LibraryTable.Cols.ID +"="+ person.getLibraryId(),null));
                libraryCursor.moveToFirst();
                Library.map.put(person.getLibraryId(),libraryCursor.getLibrary());
            }

        }
        cursor.close();
        return result;
    }

    public void save(Person c) {
        insertStatement.clearBindings();
//        insertStatement.bindLong(1,c.getId());
        insertStatement.bindString(1, c.getFirstName());
        insertStatement.bindString(2, c.getSecondName());
        insertStatement.bindString(3, c.getBirthdayDate().toString());
        insertStatement.bindString(4, c.getGender());
        insertStatement.bindLong(5, c.getPhone());
        insertStatement.bindLong(6, c.getLibrary().getId());
        insertStatement.executeInsert();
    }

    public void delete(Person c) {
        String id = String.valueOf(c.getId());
        mDatabase.delete(PersonTable.NAME,
                PersonTable.Cols.ID + " = ?",
                new String[]{id});
    }

    public Person get(long id) {
        try (PersonCursorWrapper cursor = query(
                PersonTable.Cols.ID + " = ?",
                new String[]{String.valueOf(id)})) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPerson();
        }
    }

    public void update(Person person) {
        updateStatement.clearBindings();
        updateStatement.bindString(1, person.getFirstName());
        updateStatement.bindString(2, person.getSecondName());
        updateStatement.bindLong(3, person.getId());
        updateStatement.executeUpdateDelete();
    }

    protected PersonCursorWrapper query(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                PersonTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new PersonCursorWrapper(cursor);
    }
}
