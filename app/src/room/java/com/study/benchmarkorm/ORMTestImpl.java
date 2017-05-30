package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.db.AppDatabase;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;


public class ORMTestImpl extends ORMTest {
    private AppDatabase mDb;

    public ORMTestImpl(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public void initDB(Context context) {
        mDb = AppDatabase.getDatabase(context);
    }

    @Override
    public void writeSimple(List<Book> books) {
        mDb.beginTransaction();
        for (Book book:books){
            mDb.bookModel().insert(book);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        mDb.beginTransaction();
        List<Book> books = mDb.bookModel().findAll(booksQuantity);
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        return books;
    }

    @Override
    public void updateSimple(List<Book> books) {
        mDb.beginTransaction();
        for (Book book:books){
            mDb.bookModel().update(book);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    @Override
    public void deleteSimple(List<Book> books) {
        mDb.beginTransaction();
        for (Book book:books){
            mDb.bookModel().delete(book);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        mDb.beginTransaction();
        for (Library lib:libraries){
            mDb.libraryModel().insert(lib);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        mDb.beginTransaction();
        for (Person person:persons){
            mDb.personModel().insert(person);
        }
        for (Book book:books){
            mDb.bookModel().insert(book);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        mDb.beginTransaction();
        List<Library> libraries = mDb.libraryModel().findAll(librariesQuantity);
        for (Library library:libraries){
            Library.map.put(library.getId(),library);
        } // bottleneck
        List<Book> books = mDb.bookModel().findAll(booksQuantity);
        List<Person> persons  = mDb.personModel().loadAll(personsQuantity);
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        mDb.beginTransaction();
        for (Library lib:libraries){
            mDb.libraryModel().update(lib);
        }
        for (Person person:persons){
            mDb.personModel().update(person);
        }
        for (Book book:books){
            mDb.bookModel().update(book);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        mDb.beginTransaction();
        for (Person person:persons){
            mDb.personModel().delete(person);
        }
        for (Book book:books){
            mDb.bookModel().delete(book);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        mDb.beginTransaction();
        for (Library lib:libraries){
            mDb.libraryModel().delete(lib);
        }

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

}
