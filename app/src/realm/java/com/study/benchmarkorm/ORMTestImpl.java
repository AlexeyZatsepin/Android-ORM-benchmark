package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ORMTestImpl extends ORMTest{

    private Realm realm;

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.clear(Book.class);
        realm.clear(Person.class);
        realm.clear(Library.class);
        realm.commitTransaction();
        randomObjectsGenerator = new RealmObjectGeneratorWrapper();
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
        return result;
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
        realm.clear(Book.class);
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
        List<Person> persons = realm.where(Person.class).findAll();
        List<Library> libraries = realm.where(Library.class).findAll();
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
        realm.clear(Book.class);
        realm.clear(Person.class);
        realm.clear(Library.class);
        realm.commitTransaction();
    }

    @Override
    public boolean isSimpleEmpty() {
        return realm.where(Book.class).count()==0;
    }

    @Override
    public boolean isComplexEmpty() {
        return (realm.where(Book.class).count()==0)&&(realm.where(Library.class).count()==0)&&(realm.where(Person.class).count()==0);
    }

    @Override
    public long updateSimple() {
        if (isSimpleEmpty()){
            writeSimple();
        }
        final int booksBatchNumber = 1000;

        final int numberOfPasses = 10;
        // warming-up
        for (int i = 0; i < numberOfPasses; i++) {
            List<Book> result = readSimple(booksBatchNumber);
            List<Book> books = new ArrayList<>();
            books.addAll(result);
            realm.beginTransaction();
            for (Book book: books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
//            updateSimple(books);
        }

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            List<Book> result = readSimple(booksBatchNumber);
            List<Book> books = new ArrayList<>();
            books.addAll(result);
            simpleProfiler.start();
            realm.beginTransaction();
            for (Book book: books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
//            updateSimple(books);

            allTime[i] = simpleProfiler.stop();
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    @Override
    protected long updateComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        final int numberOfPasses = 10;
        // warming-up
//        for (int i = 0; i < numberOfPasses; i++) {
//            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
//            List<Library> libraries = readed.first;
//            List<Book> books = readed.second.first;
//            List<Person> persons = readed.second.second;
//
//            for (Library library: libraries) {
//                library.setName(randomObjectsGenerator.nextString());
//            }
//
//            for (Book book: books) {
//                book.setAuthor(randomObjectsGenerator.nextString());
//            }
//
//            for (Person person: persons) {
//                person.setFirstName(randomObjectsGenerator.nextString());
//                person.setSecondName(randomObjectsGenerator.nextString());
//            }
//            updateComplex(libraries, books, persons);
//        }

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
        simpleProfiler.start();

        for (int i = 0; i < numberOfPasses; i++) {
            List<Library> libraries = readed.first;
            List<Book> books = readed.second.first;
            List<Person> persons = readed.second.second;
            List<Library> tempL = new ArrayList<>();
            List<Person> tempP = new ArrayList<>();
            List<Book> tempB = new ArrayList<>();
            tempB.addAll(books);
            tempP.addAll(persons);
            tempL.addAll(libraries);
            realm.beginTransaction();
            for (Library library: tempL) {
                library.setName(randomObjectsGenerator.nextString());
            }

            for (Book book: tempB) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }

            for (Person person: tempP) {
                person.setFirstName(randomObjectsGenerator.nextString());
                person.setSecondName(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
//            updateComplex(libraries, books, persons);
            allTime[i] = simpleProfiler.stop();

        }


        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    @Override
    protected long writeComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        if (!isComplexEmpty()){
            deleteComplex();
        }
        final int numberOfPasses = 10;
        final List<Book> books = new RealmList<>();
        final List<Person> persons = new RealmList<>();
        final List<Library> libraries = new RealmList<>();
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        // warming-up
//        for (int i = 0; i < numberOfPasses; i++) {
//            for (int j = 0; j < librariesBatchNumber; j++) {
//                oneLibraryBooks = randomObjectsGenerator.generateBooks(booksBatchNumber);
//                oneLibraryPersons = randomObjectsGenerator.generatePersons(personsBatchNumber);
//                libraries.add(randomObjectsGenerator.nextLibrary(oneLibraryBooks, oneLibraryPersons));
//                books.addAll(oneLibraryBooks);
//                persons.addAll(oneLibraryPersons);
//                randomObjectsGenerator.refresh();
//            }
//            writeComplex(libraries, books, persons);
//            deleteComplex(libraries, books, persons);
//            libraries.clear();
//            books.clear();
//            persons.clear();
//        }

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            for (int j = 0; j < librariesBatchNumber; j++) {
                oneLibraryBooks = randomObjectsGenerator.generateBooks(booksBatchNumber);
                oneLibraryPersons = randomObjectsGenerator.generatePersons(personsBatchNumber);
                libraries.add(randomObjectsGenerator.nextLibrary(oneLibraryBooks, oneLibraryPersons));
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }

            simpleProfiler.start();
            writeComplex(libraries, books, persons);
            allTime[i] = simpleProfiler.stop();

            libraries.clear();
            books.clear();
            persons.clear();

        }
        randomObjectsGenerator.refresh();
        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }
}