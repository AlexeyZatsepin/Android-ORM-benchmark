package com.study.benchmarkorm;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Models;
import com.study.benchmarkorm.model.Person;

import java.io.Serializable;
import java.util.List;

import io.requery.Transaction;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import io.requery.util.function.Consumer;

public class ORMTestImpl extends ORMTest {
    private DatabaseSource source;
    private Configuration configuration;
    private EntityDataStore<Serializable> dataStore;

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        source = new DatabaseSource(context, Models.DEFAULT, 1);
        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        configuration = source.getConfiguration();
        dataStore = new EntityDataStore<>(configuration);
    }

    @Override
    public void writeSimple(List<Book> books) {
        dataStore.insert(books);
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        return dataStore.select(Book.class).limit(booksQuantity).get().toList();
    }

    @Override
    public void updateSimple(List<Book> books) {
        dataStore.update(books);
    }

    @Override
    public void deleteSimple(List<Book> books) {
        try(Transaction transaction = dataStore.transaction()) {
            transaction.begin();
            for (Book book : books) {
                dataStore.delete(book);
            }
            transaction.commit();
        }
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        dataStore.insert(libraries);
        dataStore.insert(books);
        dataStore.insert(persons);
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        List<Library> libraries = dataStore.select(Library.class).limit(librariesQuantity).get().toList();
        List<Book> books = dataStore.select(Book.class).limit(booksQuantity).get().toList();
        List<Person> persons = dataStore.select(Person.class).limit(personsQuantity).get().toList();
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        dataStore.update(books);
        dataStore.update(persons);
        dataStore.update(libraries);
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        try(Transaction transaction = dataStore.transaction()) {
            transaction.begin();
            for (Book book : books) {
                dataStore.delete(book);
            }
            transaction.commit();
        }
        try(Transaction transaction = dataStore.transaction()) {
            transaction.begin();
            for (Person person : persons) {
                dataStore.delete(person);
            }
            transaction.commit();
        }
        try(Transaction transaction = dataStore.transaction()) {
            transaction.begin();
            for (Library library : libraries) {
                dataStore.delete(library);
            }
            transaction.commit();
        }
    }
}
