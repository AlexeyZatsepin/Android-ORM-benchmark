package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ORMTestImpl extends ORMTest{

    private Realm realm;

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void writeSimple(List<Book> books) {
        realm.beginTransaction();
        realm.copyToRealm(books);
        realm.commitTransaction();
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        realm.beginTransaction();
        RealmResults<Book> result = realm.where(Book.class).findAll();
        realm.commitTransaction();
        return result.subList(0, Math.min(booksQuantity, result.size()));
    }

    @Override
    public void updateSimple(List<Book> books) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(books);
        realm.commitTransaction();
    }

    @Override
    public void deleteSimple(List<Book> books) {
        realm.beginTransaction();
        int size = books.size();
        for (int i = 0; i < size; i++) {
            Book book = books.get(0);
            book.removeFromRealm();
        }
        realm.commitTransaction();
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        realm.beginTransaction();
        realm.copyToRealm(libraries);
        realm.commitTransaction();
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        realm.beginTransaction();
        List<Book> books = realm.where(Book.class).findAll();
        books = books.subList(0, Math.min(booksQuantity, books.size()));
        List<Person> persons = realm.where(Person.class).findAll();
        persons = persons.subList(0, Math.min(personsQuantity, persons.size()));
        List<Library> libraries = realm.where(Library.class).findAll();
        libraries = libraries.subList(0, Math.min(librariesQuantity, libraries.size()));
        realm.commitTransaction();
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(books);
        realm.copyToRealmOrUpdate(persons);
        realm.copyToRealmOrUpdate(libraries);
        realm.commitTransaction();
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        realm.beginTransaction();
        int size = books.size();
        for (int i = 0; i < size; i++) {
            Book book = books.get(0);
            book.removeFromRealm();
        }
        size = persons.size();
        for (int i = 0; i < size; i++) {
            Person person = persons.get(0);
            person.removeFromRealm();
        }
        realm.commitTransaction();
        
        realm.beginTransaction();
        size = libraries.size();
        for (int i = 0; i < size; i++) {
            Library library = libraries.get(0);
            library.removeFromRealm();
        }
        realm.commitTransaction();
    }

    @Override
    public boolean isEmpty() {
        return realm.where(Book.class).count()==0 && realm.where(Person.class).count()==0&& realm.where(Library.class).count()==0;
    }

    @Override
    public float[] updateSimple() {
        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = readSimple(BOOKS_SIMPLE_BATCH_SIZE);
            simpleProfiler.start();
            realm.beginTransaction();
            for (int j = 0; j < books.size(); j++) {
                Book book = books.get(j);
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
//            updateSimple(books);
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }

        return allTime;
    }

    @Override
    protected float[] updateComplexBenchmark(int booksBatchSize, int librariesBatchSize, int personsBatchSize) {
        booksBatchSize *= librariesBatchSize;
        personsBatchSize *= librariesBatchSize;

        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchSize, booksBatchSize, personsBatchSize);
            List<Library> libraries = readed.first;
            List<Book> books = readed.second.first;
            List<Person> persons = readed.second.second;

            simpleProfiler.start();
            realm.beginTransaction();
            for (int j = 0; j < libraries.size(); j++) {
                Library library = libraries.get(j);
                library.setName(randomObjectsGenerator.nextString());
            }

            for (int j = 0; j < books.size(); j++) {
                Book book = books.get(j);
                book.setAuthor(randomObjectsGenerator.nextString());
            }

            for (int j = 0; j < persons.size(); j++) {
                Person person = persons.get(j);
                person.setFirstName(randomObjectsGenerator.nextString());
                person.setSecondName(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
//            updateComplex(libraries, books, persons);
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }

        return allTime;
    }
}
