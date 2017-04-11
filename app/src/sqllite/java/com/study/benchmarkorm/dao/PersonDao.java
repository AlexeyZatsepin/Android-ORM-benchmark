package com.study.benchmarkorm.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.benchmarkorm.db.CursorWrappers.PersonCursorWrapper;
import com.study.benchmarkorm.db.LibraryDbSchema.PersonTable;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.List;


public class PersonDao {

    private SQLiteDatabase mDatabase;

    public PersonDao(SQLiteDatabase db) {
        mDatabase = db;
    }

    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        PersonCursorWrapper cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                persons.add(cursor.getPerson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return persons;
    }

    public void add(Person c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(PersonTable.NAME, null, values);
    }
    public void delete(Person c) {
        String id = String.valueOf(c.getId());
        mDatabase.delete(PersonTable.NAME,
                PersonTable.Cols.ID + " = ?",
                new String[] {id});
    }

    public Person get(long id) {
        PersonCursorWrapper cursor = query(
                PersonTable.Cols.ID + " = ?",
        new String[] {String.valueOf(id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPerson();
        } finally {
            cursor.close();
        }
    }

    public void update(Person person) {
        String id = String.valueOf(person.getId());
        ContentValues values = getContentValues(person);
        mDatabase.update(PersonTable.NAME, values,
                PersonTable.Cols.ID + " = ?",
                new String[] { id });
    }


    private static ContentValues getContentValues(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonTable.Cols.ID, person.getId());
        values.put(PersonTable.Cols.F_NAME, person.getFirstName());
        values.put(PersonTable.Cols.S_NAME, person.getSecondName());
        values.put(PersonTable.Cols.DATE, person.getBirthdayDate().getTime());
        values.put(PersonTable.Cols.GENDER, person.getGender());
        values.put(PersonTable.Cols.PHONE, person.getPhone());
        values.put(PersonTable.Cols.LIBRARY_ID, person.getLibrary().getId());
        return values;
    }

    private PersonCursorWrapper query(String whereClause, String[] whereArgs) {
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
