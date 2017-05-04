package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;
import android.widget.TextView;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class ORMTest {

    protected final int NUMBER_OF_PASSES = 10;
    protected RandomObjectsGenerator randomObjectsGenerator = new RandomObjectsGenerator();

    private static final int BOOKS_SIMPLE_BATCH_SIZE = 1000;
    private static final int LIBRARIES_BALANCED_BATCH_SIZE = 5;
    private static final int BOOKS_BALANCED_BATCH_SIZE = 50;
    private static final int PERSONS_BALANCED_BATCH_SIZE = 50;
    private static final int LIBRARIES_COMPLEX_BATCH_SIZE = 5;
    private static final int BOOKS_COMPLEX_BATCH_SIZE = 500;
    private static final int PERSONS_COMPLEX_BATCH_SIZE = 400;

    public ORMTest(Context context) {
        initDB(context);
    }

    public abstract void initDB(Context context);

    public abstract void writeSimple(List<Book> books);

    public abstract List<Book> readSimple(int booksQuantity);

    public abstract void updateSimple(List<Book> books);

    public abstract void deleteSimple(List<Book> books);

    public abstract void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity);

    public abstract void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public boolean isEmpty() {
        return readSimple(1).isEmpty();
    }

    public void warmingUp() {
        final List<Book> books = new ArrayList<>();
        final List<Person> persons = new ArrayList<>();
        final List<Library> libraries = new ArrayList<>();
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                final Library library = randomObjectsGenerator.nextLibrary();
                writeComplex(new ArrayList<Library>() {{
                    add(library);
                }}, new ArrayList<Book>(), new ArrayList<Person>());
                List<Library> bufLibraries = readComplex(Math.max(LIBRARIES_BALANCED_BATCH_SIZE, LIBRARIES_COMPLEX_BATCH_SIZE) + 2, 0, 0)
                        .first;
                libraries.add(bufLibraries.get(bufLibraries.size() - 1));

                oneLibraryBooks = randomObjectsGenerator.generateBooks(10, libraries.get(j));
                oneLibraryPersons = randomObjectsGenerator.generatePersons(10, libraries.get(j));
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }
            writeComplex(new ArrayList<Library>(), books, persons);
            libraries.clear();
            books.clear();
            persons.clear();
        }
        Pair<List<Library>, Pair<List<Book>, List<Person>>> data =
                readComplex(10,
                        100, 100);
        deleteComplex(data.first,
                data.second.first,
                data.second.second);

        System.gc();
    }

    public float[] writeSimple(int writeNumber) {
        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        final Library library = randomObjectsGenerator.nextLibrary();
        writeComplex(new ArrayList<Library>() {{
            add(library);
        }}, new ArrayList<Book>(), new ArrayList<Person>());
        Library readedLibrary = readComplex(writeNumber + 1, 0, 0).first.get(writeNumber);

        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = randomObjectsGenerator.generateBooks(BOOKS_SIMPLE_BATCH_SIZE, readedLibrary);
            simpleProfiler.start();
            writeSimple(books);
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }

        return allTime;
    }

    public float[] readSimple() {
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            simpleProfiler.start();
            List<Book> books = readSimple(BOOKS_SIMPLE_BATCH_SIZE);
            allTime[i] = simpleProfiler.stop();
            deleteSimple(books);

            System.gc();
        }

        return allTime;
    }

    public float[] updateSimple() {
        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = readSimple(BOOKS_SIMPLE_BATCH_SIZE);
            for (Book book : books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            simpleProfiler.start();
            updateSimple(books);
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }

        return allTime;
    }

    public float[] deleteSimple() {
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = readSimple(BOOKS_SIMPLE_BATCH_SIZE);
            simpleProfiler.start();
            deleteSimple(books);
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }

        return allTime;
    }

    public float[] writeBalanced(int writeNumber) {
        return writeComplexBenchmark(BOOKS_BALANCED_BATCH_SIZE, LIBRARIES_BALANCED_BATCH_SIZE, PERSONS_BALANCED_BATCH_SIZE, writeNumber);
    }

    protected float[] writeComplexBenchmark(int booksBatchSize, int librariesBatchSize, int personsBatchSize, int writeNumber) {

        final List<Book> books = new ArrayList<>(booksBatchSize * librariesBatchSize);
        final List<Person> persons = new ArrayList<>(personsBatchSize * librariesBatchSize);
        List<Library> libraries;
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            allTime[i] = 0;

            //libraries
            for (int j = 0; j < librariesBatchSize; j++) {
                final Library library = randomObjectsGenerator.nextLibrary();

                simpleProfiler.start();
                writeComplex(new ArrayList<Library>() {{
                    add(library);
                }}, new ArrayList<Book>(), new ArrayList<Person>());
                allTime[i] += simpleProfiler.stop();
            }
        }

        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            libraries = readComplex((i + 1) * librariesBatchSize * (writeNumber + 1), 0, 0).first
                    .subList(i * librariesBatchSize * writeNumber,
                            (i + 1) * librariesBatchSize * (writeNumber + 1));

            //books and persons
            for (int j = 0; j < librariesBatchSize; j++) {
                oneLibraryBooks = randomObjectsGenerator.generateBooks(booksBatchSize, libraries.get(j));
                oneLibraryPersons = randomObjectsGenerator.generatePersons(personsBatchSize, libraries.get(j));
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }

            simpleProfiler.start();
            writeComplex(libraries, books, persons);
            allTime[i] += simpleProfiler.stop();

            books.clear();
            persons.clear();

            System.gc();
        }

        return allTime;
    }

    public float[] readBalanced() {
        return readComplexBenchmark(BOOKS_BALANCED_BATCH_SIZE, LIBRARIES_BALANCED_BATCH_SIZE, PERSONS_BALANCED_BATCH_SIZE);
    }

    protected float[] readComplexBenchmark(int booksBatchSize, int librariesBatchSize, int personsBatchSize) {
        booksBatchSize *= librariesBatchSize;
        personsBatchSize *= librariesBatchSize;

        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            simpleProfiler.start();
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(librariesBatchSize, booksBatchSize, personsBatchSize);
            allTime[i] = simpleProfiler.stop();
            deleteComplex(data.first, data.second.first, data.second.second);

            System.gc();
        }

        return allTime;
    }

    public float[] updateBalanced() {
        return updateComplexBenchmark(BOOKS_BALANCED_BATCH_SIZE, LIBRARIES_BALANCED_BATCH_SIZE, PERSONS_BALANCED_BATCH_SIZE);
    }

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

            for (Library library : libraries) {
                library.setName(randomObjectsGenerator.nextString());
            }

            for (Book book : books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }

            for (Person person : persons) {
                person.setFirstName(randomObjectsGenerator.nextString());
                person.setSecondName(randomObjectsGenerator.nextString());
            }

            simpleProfiler.start();
            updateComplex(libraries, books, persons);
            allTime[i] = simpleProfiler.stop();


            System.gc();
        }

        return allTime;
    }

    public float[] deleteBalanced() {
        return deleteComplexBenchmark(BOOKS_BALANCED_BATCH_SIZE, LIBRARIES_BALANCED_BATCH_SIZE, PERSONS_BALANCED_BATCH_SIZE);
    }

    protected float[] deleteComplexBenchmark(int booksBatchSize, int librariesBatchSize, int personsBatchSize) {
        booksBatchSize *= librariesBatchSize;
        personsBatchSize *= librariesBatchSize;

        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(librariesBatchSize, booksBatchSize, personsBatchSize);
            simpleProfiler.start();
            deleteComplex(data.first, data.second.first, data.second.second);
            allTime[i] = simpleProfiler.stop();

            System.gc();
        }

        return allTime;
    }

    public float[] writeComplex(int writeNumber) {
        return writeComplexBenchmark(BOOKS_COMPLEX_BATCH_SIZE,
                LIBRARIES_COMPLEX_BATCH_SIZE, PERSONS_COMPLEX_BATCH_SIZE, writeNumber);
    }

    public float[] readComplex() {
        return readComplexBenchmark(BOOKS_COMPLEX_BATCH_SIZE, LIBRARIES_COMPLEX_BATCH_SIZE, PERSONS_COMPLEX_BATCH_SIZE);
    }

    public float[] updateComplex() {
        return updateComplexBenchmark(BOOKS_COMPLEX_BATCH_SIZE, LIBRARIES_COMPLEX_BATCH_SIZE, PERSONS_COMPLEX_BATCH_SIZE);
    }

    public float[] deleteComplex() {
        return deleteComplexBenchmark(BOOKS_COMPLEX_BATCH_SIZE, LIBRARIES_COMPLEX_BATCH_SIZE, PERSONS_COMPLEX_BATCH_SIZE);
    }
}