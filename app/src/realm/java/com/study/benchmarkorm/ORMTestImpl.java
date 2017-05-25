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
        randomObjectsGenerator = new RealmObjectGeneratorWrapper(realm);
    }

    @Override
    public void writeSimple(List<Book> books) {
        // look with realmList
    }


    public void writeSimple(RealmList<Book> books) {
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
    public void updateSimple(List<Book> books) {}


//    public void updateSimple(RealmList<Book> books) {
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(books);
//        realm.commitTransaction();
//    }

    @Override
    public void deleteSimple(List<Book> books) {
        realm.beginTransaction();
        for (Book book:books){
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
    public boolean isEmpty() {
        return realm.where(Book.class).count()==0 && realm.where(Person.class).count()==0&& realm.where(Library.class).count()==0;
    }

    @Override
    public void warmingUp() {
        final RealmList<Book> books = new RealmList<>();
        final RealmList<Person> persons = new RealmList<>();
        final RealmList<Library> libraries = new RealmList<>();
        RealmList<Book> oneLibraryBooks;
        RealmList<Person> oneLibraryPersons;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                realm.beginTransaction();
                oneLibraryBooks = ((RealmObjectGeneratorWrapper)randomObjectsGenerator).generateBooks(10);
                oneLibraryPersons = ((RealmObjectGeneratorWrapper)randomObjectsGenerator).generatePersons(10);
                libraries.add(((RealmObjectGeneratorWrapper)randomObjectsGenerator).nextLibrary(oneLibraryBooks,oneLibraryPersons));
                realm.commitTransaction();
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }
            writeComplex(libraries, books, persons);
            deleteComplex(libraries, books, persons);
            libraries.clear();
            books.clear();
            persons.clear();
        }
    }

    @Override
    public float[] writeSimple(int writeNumber) {
        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
//            RealmList<Book> books = ((RealmObjectGeneratorWrapper)randomObjectsGenerator).generateBooks(BOOKS_SIMPLE_BATCH_SIZE);
            simpleProfiler.start();
//            writeSimple(books);
            realm.beginTransaction();
            RealmList<Book> books = ((RealmObjectGeneratorWrapper)randomObjectsGenerator).generateBooks(BOOKS_SIMPLE_BATCH_SIZE);
            realm.commitTransaction();
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }
        return allTime;
    }

    @Override
    public float[] updateSimple() {
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = readSimple(BOOKS_SIMPLE_BATCH_SIZE);
            simpleProfiler.start();
            realm.beginTransaction();
            for (Book book: books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
            allTime[i] = simpleProfiler.stop();
        }
        return allTime;
    }

    @Override
    protected float[] writeComplexBenchmark(int booksBatchSize, int librariesBatchSize, int personsBatchSize, int writeNumber) {
        final RealmList<Book> books = new RealmList<>();
        final RealmList<Person> persons = new RealmList<>();
        final RealmList<Library> libraries = new RealmList<>();
        RealmList<Book> oneLibraryBooks;
        RealmList<Person> oneLibraryPersons;

        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            for (int j = 0; j < LIBRARIES_COMPLEX_BATCH_SIZE; j++) {
                simpleProfiler.start();
                realm.beginTransaction();
                oneLibraryBooks = ((RealmObjectGeneratorWrapper)randomObjectsGenerator).generateBooks(BOOKS_COMPLEX_BATCH_SIZE);
                oneLibraryPersons = ((RealmObjectGeneratorWrapper)randomObjectsGenerator).generatePersons(PERSONS_COMPLEX_BATCH_SIZE);
                libraries.add(((RealmObjectGeneratorWrapper)randomObjectsGenerator).nextLibrary(oneLibraryBooks, oneLibraryPersons));
                realm.commitTransaction();
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
                allTime[i] = simpleProfiler.stop();
            }

//            simpleProfiler.start();
//            writeComplex(libraries, books, persons);
//            allTime[i] = simpleProfiler.stop();

            libraries.clear();
            books.clear();
            persons.clear();
        }
        return allTime;
    }

    @Override
    protected float[] updateComplexBenchmark(int booksBatchSize, int librariesBatchSize, int personsBatchSize) {
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchSize, booksBatchSize, personsBatchSize);
            List<Library> libraries = readed.first;
            List<Book> books = readed.second.first;
            List<Person> persons = readed.second.second;

            simpleProfiler.start();
            realm.beginTransaction();
            for (Library library: libraries) {
                library.setName(randomObjectsGenerator.nextString());
            }

            for (Book book: books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }

            for (Person person: persons) {
                person.setFirstName(randomObjectsGenerator.nextString());
                person.setSecondName(randomObjectsGenerator.nextString());
            }
            realm.commitTransaction();
            allTime[i] = simpleProfiler.stop();
        }
        return allTime;
    }

    @Override
    public boolean checkIfLoaded(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Library library: libraries) {
            if (library.getName() == null) {
                return false;
            }
            if (library.getAddress() == null) {
                return false;
            }
            if (library.getEmployees() == null){
                return false;
            }
            if (library.getBooks() == null){
                return false;
            }
        }
        for (Person person: persons) {
            if (person.getFirstName() == null) {
                return false;
            }
            if (person.getSecondName() == null) {
                return false;
            }
            if (person.getBirthdayDate() == null) {
                return false;
            }
            if (person.getGender() == null) {
                return false;
            }
        }
        for (Book book: books) {
            if (book.getAuthor() == null) {
                return false;
            }
            if (book.getTitle() == null) {
                return false;
            }
        }
        return true;
    }
}
