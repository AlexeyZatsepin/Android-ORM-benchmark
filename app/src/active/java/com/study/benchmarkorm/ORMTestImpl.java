package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;


public class ORMTestImpl extends ORMTest {
    public ORMTestImpl(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public void initDB(Context context) {

    }

    @Override
    public void writeSimple(List<Book> books) {
        ActiveAndroid.beginTransaction();
        try{
            for (Book book: books){
                book.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally{
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        ActiveAndroid.beginTransaction();
        List<Book> result;
        try{
            result = new Select().from(Book.class).limit(booksQuantity).execute();
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
        return result;
    }

    @Override
    public void updateSimple(List<Book> books) {
        ActiveAndroid.beginTransaction();
        try{
            for (Book book: books){
                book.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally{
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public void deleteSimple(List<Book> books) {
        ActiveAndroid.beginTransaction();
        try{
            for (Book book: books){
                book.delete();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally{
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        ActiveAndroid.beginTransaction();
        try{
            for (Book book: books){
                book.save();
            }
            for (Person person:persons){
                person.save();
            }
            for (Library lib:libraries){
                lib.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally{
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        ActiveAndroid.beginTransaction();
        List<Book> books;
        List<Person> persons;
        List<Library> libraries;
        try{
            books = new Select().from(Book.class).limit(booksQuantity).execute();
            persons = new Select().from(Person.class).limit(personsQuantity).execute();
            libraries = new Select().from(Library.class).limit(librariesQuantity).execute();
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        ActiveAndroid.beginTransaction();
        try{
            for (Book book: books){
                book.save();
            }
            for (Person person:persons){
                person.save();
            }
            for (Library lib:libraries){
                lib.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally{
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        ActiveAndroid.beginTransaction();
        try{
            for (Book book: books){
                book.delete();
            }
            for (Person person:persons){
                person.delete();
            }
            for (Library lib:libraries){
                lib.delete();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally{
            ActiveAndroid.endTransaction();
        }
    }
}
