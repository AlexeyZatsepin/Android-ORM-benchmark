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

    public ORMTest(Context context) {
        initDB(context);
    }

//    public void testSimplePartOne(TestListener writeTestListener) {
//        // warming-up
//        final Library library = randomObjectsGenerator.nextLibrary();
//        writeComplex(new ArrayList<Library>(){{add(library);}}, new ArrayList<Book>(), new ArrayList<Person>());
//        for (int i = 0; i < 5; i++) {
//            writeSimple(randomObjectsGenerator.generateBooks(100, library));
//            deleteSimple(readSimple(100));
//        }
//        Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(1, 0, 0);
//        deleteComplex(data.first, data.second.first, data.second.second);
//
//        // main
//        float[] first = writeSimple();
//        float[] second = writeSimple();
//        float[] third = writeSimple();
//        writeTestListener.onTestFinished(third);
//    }
//
//    public void testSimplePartTwo(TestListener readTestListener,
//                                  TestListener updateTestListener, TestListener deleteTestListener) {
//        // warming-up
//        readSimple();
//
//        float[] readTime = readSimple();
//        readTestListener.onTestFinished(readTime);
//        float[] updateTime = updateSimple();
//        updateTestListener.onTestFinished(updateTime);
//        float[] deleteTime = deleteSimple();
//        deleteTestListener.onTestFinished(deleteTime);
//    }
//
//    public void testComplexPartOne(TestListener writeTestListener) {
//        // warming-up
//        final List<Book> books = new ArrayList<>();
//        final List<Person> persons = new ArrayList<>();
//        final List<Library> libraries = new ArrayList<>();
//        List<Book> oneLibraryBooks;
//        List<Person> oneLibraryPersons;
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 2; j++) {
//                Library library = randomObjectsGenerator.nextLibrary();
//                libraries.add(library);
//                oneLibraryBooks = randomObjectsGenerator.generateBooks(10, library);
//                oneLibraryPersons = randomObjectsGenerator.generatePersons(10, library);
//                books.addAll(oneLibraryBooks);
//                persons.addAll(oneLibraryPersons);
//            }
//            writeComplex(libraries, books, persons);
//            Pair<List<Library>, Pair<List<Book>, List<Person>>> data =
//                    readComplex(libraries.size(), books.size(), persons.size());
//            deleteComplex(data.first, data.second.first, data.second.second);
//            libraries.clear();
//            books.clear();
//            persons.clear();
//        }
//
//
//        float[] first = writeComplex();
//        float[] second = writeComplex();
//        float[] third = writeComplex();
//        writeTestListener.onTestFinished(third);
//    }
//
//    public void testComplexPartTwo(TestListener readTestListener,
//                                   TestListener updateTestListener, TestListener deleteTestListener) {
//        // warming-up
//        readComplex();
//
//        //main
//        float[] readTime = readComplex();
//        readTestListener.onTestFinished(readTime);
//        float[] updateTime = updateComplex();
//        updateTestListener.onTestFinished(updateTime);
//        float[] deleteTime = deleteComplex();
//        deleteTestListener.onTestFinished(deleteTime);
//    }
//
//    public void testBalancedPartOne(TestListener writeTestListener) {
//        // warming-up
//        final List<Book> books = new ArrayList<>();
//        final List<Person> persons = new ArrayList<>();
//        final List<Library> libraries = new ArrayList<>();
//        List<Book> oneLibraryBooks;
//        List<Person> oneLibraryPersons;
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 2; j++) {
//                Library library = randomObjectsGenerator.nextLibrary();
//                libraries.add(library);
//                oneLibraryBooks = randomObjectsGenerator.generateBooks(10, library);
//                oneLibraryPersons = randomObjectsGenerator.generatePersons(10, library);
//                books.addAll(oneLibraryBooks);
//                persons.addAll(oneLibraryPersons);
//            }
//            writeComplex(libraries, books, persons);
//            Pair<List<Library>, Pair<List<Book>, List<Person>>> data =
//                    readComplex(libraries.size(), books.size(), persons.size());
//            deleteComplex(data.first, data.second.first, data.second.second);
//            libraries.clear();
//            books.clear();
//            persons.clear();
//        }
//
//
//        float[] first = writeComplex();
//        float[] second = writeComplex();
//        float[] third = writeComplex();
//        writeTestListener.onTestFinished(third);
//    }
//
//    public void testBalancedPartTwo(TestListener readTestListener,
//                                    TestListener updateTestListener, TestListener deleteTestListener) {
//        // warming-up
//        readBalanced();
//
//        float[] readTime = readBalanced();
//        readTestListener.onTestFinished(readTime);
//        float[] updateTime = updateBalanced();
//        updateTestListener.onTestFinished(updateTime);
//        float[] deleteTime = deleteBalanced();
//        deleteTestListener.onTestFinished(deleteTime);
//    }

    public abstract void initDB(Context context);

    public abstract void writeSimple(List<Book> books);

    public abstract List<Book> readSimple(int booksQuantity);

    public abstract void updateSimple(List<Book> books);

    public abstract void deleteSimple(List<Book> books);

    public abstract void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity);

    public abstract void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons);

    public abstract boolean isEmpty();

    public void warmingUp() {
        final List<Book> books = new ArrayList<>();
        final List<Person> persons = new ArrayList<>();
        final List<Library> libraries = new ArrayList<>();
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                Library library = randomObjectsGenerator.nextLibrary();
                libraries.add(library);
                oneLibraryBooks = randomObjectsGenerator.generateBooks(10, library);
                oneLibraryPersons = randomObjectsGenerator.generatePersons(10, library);
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }
            writeComplex(libraries, books, persons);
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data =
                    readComplex(libraries.size(), books.size(), persons.size());
            deleteComplex(data.first, data.second.first, data.second.second);
            libraries.clear();
            books.clear();
            persons.clear();
        }
    }

    public float[] writeSimple() {
        final int booksBatchNumber = 1000;


        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        Library library = randomObjectsGenerator.nextLibrary();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = randomObjectsGenerator.generateBooks(booksBatchNumber, library);
            simpleProfiler.start();
            writeSimple(books);
            allTime[i] = simpleProfiler.stop();
        }

        return allTime;
    }

    public float[] readSimple() {
        final int booksBatchNumber = 1000;


        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            simpleProfiler.start();
            List<Book> books = readSimple(booksBatchNumber);
            allTime[i] = simpleProfiler.stop();
            deleteSimple(books);
        }

        return allTime;
    }

    public float[] updateSimple() {
        final int booksBatchNumber = 1000;


        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = readSimple(booksBatchNumber);
            for (Book book : books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            simpleProfiler.start();
            updateSimple(books);
            allTime[i] = simpleProfiler.stop();
        }

        return allTime;
    }

    public float[] deleteSimple() {
        final int booksBatchNumber = 1000;

        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            List<Book> books = readSimple(booksBatchNumber);
            simpleProfiler.start();
            deleteSimple(books);
            allTime[i] = simpleProfiler.stop();
        }

        return allTime;
    }

    public float[] writeBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return writeComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected float[] writeComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {

        final List<Book> books = new ArrayList<>(booksBatchNumber * librariesBatchNumber);
        final List<Person> persons = new ArrayList<>(personsBatchNumber * librariesBatchNumber);
        final List<Library> libraries = new ArrayList<>(librariesBatchNumber);
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            for (int j = 0; j < librariesBatchNumber; j++) {
                Library library = randomObjectsGenerator.nextLibrary();
                oneLibraryBooks = randomObjectsGenerator.generateBooks(booksBatchNumber, library);
                oneLibraryPersons = randomObjectsGenerator.generatePersons(personsBatchNumber, library);
                libraries.add(library);
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

        return allTime;
    }

    public float[] readBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return readComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected float[] readComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {

        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            simpleProfiler.start();
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            allTime[i] = simpleProfiler.stop();
            deleteComplex(data.first, data.second.first, data.second.second);
        }

        return allTime;
    }

    public float[] updateBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return updateComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected float[] updateComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {


        // warming-up
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
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
            updateComplex(libraries, books, persons);
        }

        // main part
        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
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

        }

        return allTime;
    }

    public float[] deleteBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return deleteComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected float[] deleteComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {


        float[] allTime = new float[NUMBER_OF_PASSES];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            simpleProfiler.start();
            deleteComplex(data.first, data.second.first, data.second.second);
            allTime[i] = simpleProfiler.stop();
        }

        return allTime;
    }

    public float[] writeComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return writeComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public float[] readComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return readComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public float[] updateComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return updateComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public float[] deleteComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return deleteComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }
}