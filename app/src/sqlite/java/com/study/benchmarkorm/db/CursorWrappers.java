package com.study.benchmarkorm.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;
import com.study.benchmarkorm.db.LibraryDbSchema.*;

import java.util.Date;

public class CursorWrappers {

    public static class BookCursorWrapper extends CursorWrapper{
        public BookCursorWrapper(Cursor cursor) {
            super(cursor);
        }
        public Book getBook() {
            long id = getLong(getColumnIndex(BookTable.Cols.ID));
            String title = getString(getColumnIndex(BookTable.Cols.TITLE));
            String author = getString(getColumnIndex(BookTable.Cols.AUTHOR));
            int pages = getInt(getColumnIndex(BookTable.Cols.PAGES_COUNT));
            int bookId = getInt(getColumnIndex(BookTable.Cols.BOOK_ID));
            long libId = getInt(getColumnIndex(BookTable.Cols.LIBRARY_ID));
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setId(id);
            book.setPagesCount(pages);
            book.setBookId(bookId);
//            book.setLibrary(libId);
            return book;
        }
    }

    public static class LibraryCursorWrapper extends CursorWrapper{
        public LibraryCursorWrapper(Cursor cursor) {
            super(cursor);
        }
        public Library getLibrary() {
            long id = getLong(getColumnIndex(LibraryTable.Cols.ID));
            String address = getString(getColumnIndex(LibraryTable.Cols.ADDRESS));
            String name = getString(getColumnIndex(LibraryTable.Cols.NAME));
            Library result = new Library();
            result.setId(id);
            result.setAddress(address);
            result.setName(name);
            return result;
        }
    }

    public static class PersonCursorWrapper extends CursorWrapper{
        public PersonCursorWrapper(Cursor cursor) {
            super(cursor);
        }
        public Person getPerson() {
            long id = getLong(getColumnIndex(PersonTable.Cols.ID));
            String fName = getString(getColumnIndex(PersonTable.Cols.F_NAME));
            String sName = getString(getColumnIndex(PersonTable.Cols.S_NAME));
            Date date = new Date(getString(getColumnIndex(PersonTable.Cols.DATE)));
            String gender = getString(getColumnIndex(PersonTable.Cols.GENDER));
            long phone = getLong(getColumnIndex(PersonTable.Cols.PHONE));
            long libId = getInt(getColumnIndex(PersonTable.Cols.LIBRARY_ID));
            Person person = new Person();
            person.setFirstName(fName);
            person.setSecondName(sName);
            person.setId(id);
            person.setGender(gender);
            person.setBirthdayDate(date);
            person.setPhone(phone);
//            person.setLibrary(libId);
            return person;
        }
    }

}
