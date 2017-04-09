package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.naminas on 4/3/2017.
 */

public abstract class ORMTest {
    private RandomObjectsGenerator randomObjectsGenerator = new RandomObjectsGenerator();

    public ORMTest() {
        initDB();
    }

    public abstract void initDB();

    public abstract void writeSimple(List<Book> books);

    public abstract void readSimple(int booksQuantity);

    public abstract void updateSimple(List<Book> books);

    public abstract void deleteSimple(int objectsQuantity);

    public abstract void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract void readComplex(int librariesQuantity, int booksQuantity, int personsQuantity);

    public abstract void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract void deleteComplex(int librariesQuantity, int booksQuantity, int personsQuantity);

    public long writeSimple() {
        final int booksBatchNumber = 1000;

        final int numberOfPasses = 10;
        // warming-up
        for (int i = 0; i < numberOfPasses; i++) {
            writeSimple(randomObjectsGenerator.generateBooks(booksBatchNumber));
            deleteSimple(booksBatchNumber);
        }

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            simpleProfiler.start();
            writeSimple(randomObjectsGenerator.generateBooks(booksBatchNumber));
            allTime[i] = simpleProfiler.stop();
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long readSimple() {

    }

    public long updateSimple() {

    }

    public long deleteSimple() {

    }

    public long writeBalanced() {
        final int booksBatchNumber = 100;
        final int librariesBatchNumber = 100;
        final int personsBatchNumber = 100;

        return writeComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    private long writeComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        final int numberOfPasses = 10;
        final List<Book> books = new ArrayList<>(booksBatchNumber * librariesBatchNumber);
        final List<Person> persons = new ArrayList<>(personsBatchNumber * librariesBatchNumber);
        final List<Library> libraries = new ArrayList<>(librariesBatchNumber);
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        // warming-up
        for (int i = 0; i < numberOfPasses; i++) {
            for (int j = 0; j < librariesBatchNumber; j++) {
                oneLibraryBooks = randomObjectsGenerator.generateBooks(booksBatchNumber);
                oneLibraryPersons = randomObjectsGenerator.generatePersons(personsBatchNumber);
                libraries.add(randomObjectsGenerator.nextLibrary(oneLibraryBooks, oneLibraryPersons));
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }
            writeComplex(libraries, books, persons);
            deleteComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            libraries.clear();
            books.clear();
            persons.clear();
        }

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

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long readBalanced() {

    }

    public long updateBalanced() {

    }

    public long deleteBalanced() {

    }

    public long writeComplex() {
        final int booksBatchNumber = 10000;
        final int librariesBatchNumber = 10;
        final int personsBatchNumber = 5000;

        return writeComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public long readComplex() {

    }

    public long updateComplex() {

    }

    public long deleteComplex() {

    }
}
