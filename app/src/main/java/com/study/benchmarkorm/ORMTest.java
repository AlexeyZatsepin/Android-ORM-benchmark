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
    protected RandomObjectsGenerator randomObjectsGenerator = new RandomObjectsGenerator();

    public ORMTest(Context context) {
        initDB(context);
    }

    public void testSimplePartOne(TextView writeTV) {
        // warming-up
        for (int i = 0; i < 5; i++) {
            writeSimple(randomObjectsGenerator.generateBooks(100));
            deleteSimple(readSimple(100));
        }
        long first = writeSimple();
        long second = writeSimple();
        long third = writeSimple();
        long average = (first + second + third) / 3;
        writeTV.setText("Write: " + average + "ms" + "\nPlease restart your app and click second part button");
    }

    public void testSimplePartTwo(TextView readTV, TextView updateTV, TextView deleteTV) {
        // warming-up
        readSimple();
        long readTime = readSimple();
        readTV.setText("Read: " + readTime + "ms");
        long updateTime = updateSimple();
        readTV.setText("Update: " + updateTime + "ms");
        long deleteTime = deleteSimple();
        readTV.setText("Delete: " + deleteTime + "ms");
    }

    public void testComplexPartOne(TextView writeTV) {
        // warming-up
        final List<Book> books = new ArrayList<>();
        final List<Person> persons = new ArrayList<>();
        final List<Library> libraries = new ArrayList<>();
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                oneLibraryBooks = randomObjectsGenerator.generateBooks(10);
                oneLibraryPersons = randomObjectsGenerator.generatePersons(10);
                libraries.add(randomObjectsGenerator.nextLibrary(oneLibraryBooks, oneLibraryPersons));
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }
            writeComplex(libraries, books, persons);
            deleteComplex(libraries, books, persons);
            libraries.clear();
            books.clear();
            persons.clear();
        }


        long first = writeComplex();
        long second = writeComplex();
        long third = writeComplex();
        long average = (first + second + third) / 3;
        writeTV.setText("Write: " + average + "ms" + "\nPlease restart your app and click second part button");
    }

    public void testComplexPartTwo(TextView readTV, TextView updateTV, TextView deleteTV) {
        // warming-up
        readComplex();
        long readTime = readComplex();
        readTV.setText("Read: " + readTime + "ms");
        long updateTime = updateComplex();
        readTV.setText("Update: " + updateTime + "ms");
        long deleteTime = deleteComplex();
        readTV.setText("Delete: " + deleteTime + "ms");
    }

    public void testBalancedPartOne(TextView writeTV) {
        // warming-up
        final List<Book> books = new ArrayList<>();
        final List<Person> persons = new ArrayList<>();
        final List<Library> libraries = new ArrayList<>();
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                oneLibraryBooks = randomObjectsGenerator.generateBooks(10);
                oneLibraryPersons = randomObjectsGenerator.generatePersons(10);
                libraries.add(randomObjectsGenerator.nextLibrary(oneLibraryBooks, oneLibraryPersons));
                books.addAll(oneLibraryBooks);
                persons.addAll(oneLibraryPersons);
            }
            writeComplex(libraries, books, persons);
            deleteComplex(libraries, books, persons);
            libraries.clear();
            books.clear();
            persons.clear();
        }


        long first = writeBalanced();
        long second = writeBalanced();
        long third = writeBalanced();
        long average = (first + second + third) / 3;
        writeTV.setText("Write: " + average + "ms" + "\nPlease restart your app and click second part button");
    }

    public void testBalancedPartTwo(TextView readTV, TextView updateTV, TextView deleteTV) {
        // warming-up
        readComplex();
        long readTime = readBalanced();
        readTV.setText("Read: " + readTime + "ms");
        long updateTime = updateBalanced();
        readTV.setText("Update: " + updateTime + "ms");
        long deleteTime = deleteBalanced();
        readTV.setText("Delete: " + deleteTime + "ms");
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

    public long writeSimple() {
        final int booksBatchNumber = 1000;

        final int numberOfPasses = 10;

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            List<Book> books = randomObjectsGenerator.generateBooks(booksBatchNumber);
            simpleProfiler.start();
            writeSimple(books);
            allTime[i] = simpleProfiler.stop();
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long readSimple() {
        final int booksBatchNumber = 1000;
        final int numberOfPasses = 10;

        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            simpleProfiler.start();
            List<Book> books = readSimple(booksBatchNumber);
            allTime[i] = simpleProfiler.stop();
            deleteSimple(books);
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long updateSimple() {
        final int booksBatchNumber = 1000;

        final int numberOfPasses = 10;

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            List<Book> books = readSimple(booksBatchNumber);
            for (Book book: books) {
                book.setAuthor(randomObjectsGenerator.nextString());
            }
            simpleProfiler.start();
            updateSimple(books);
            allTime[i] = simpleProfiler.stop();
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long deleteSimple() {
        final int booksBatchNumber = 1000;
        final int numberOfPasses = 10;

        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            List<Book> books = readSimple(booksBatchNumber);
            simpleProfiler.start();
            deleteSimple(books);
            allTime[i] = simpleProfiler.stop();
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long writeBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return writeComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected long writeComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        final int numberOfPasses = 10;
        final List<Book> books = new ArrayList<>(booksBatchNumber * librariesBatchNumber);
        final List<Person> persons = new ArrayList<>(personsBatchNumber * librariesBatchNumber);
        final List<Library> libraries = new ArrayList<>(librariesBatchNumber);
        List<Book> oneLibraryBooks;
        List<Person> oneLibraryPersons;

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
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return readComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected long readComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        final int numberOfPasses = 10;
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            simpleProfiler.start();
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            allTime[i] = simpleProfiler.stop();
            deleteComplex(data.first, data.second.first, data.second.second);
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long updateBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return updateComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected long updateComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        final int numberOfPasses = 10;

        // warming-up
        for (int i = 0; i < numberOfPasses; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            List<Library> libraries = readed.first;
            List<Book> books = readed.second.first;
            List<Person> persons = readed.second.second;

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
            updateComplex(libraries, books, persons);
        }

        // main part
        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> readed = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            List<Library> libraries = readed.first;
            List<Book> books = readed.second.first;
            List<Person> persons = readed.second.second;

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

            simpleProfiler.start();
            updateComplex(libraries, books, persons);
            allTime[i] = simpleProfiler.stop();

        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long deleteBalanced() {
        final int booksBatchNumber = 50;
        final int librariesBatchNumber = 50;
        final int personsBatchNumber = 50;

        return deleteComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    protected long deleteComplexBenchmark(int booksBatchNumber, int librariesBatchNumber, int personsBatchNumber) {
        final int numberOfPasses = 10;

        long[] allTime = new long[numberOfPasses];
        SimpleProfiler simpleProfiler = new SimpleProfiler();
        for (int i = 0; i < numberOfPasses; i++) {
            Pair<List<Library>, Pair<List<Book>, List<Person>>> data = readComplex(librariesBatchNumber, booksBatchNumber, personsBatchNumber);
            simpleProfiler.start();
            deleteComplex(data.first, data.second.first, data.second.second);
            allTime[i] = simpleProfiler.stop();
        }

        long average = 0;
        for (int i = 0; i < numberOfPasses; i++) {
            average += allTime[i] / numberOfPasses;
        }
        return average;
    }

    public long writeComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return writeComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public long readComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return readComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public long updateComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return updateComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }

    public long deleteComplex() {
        final int booksBatchNumber = 500;
        final int librariesBatchNumber = 5;
        final int personsBatchNumber = 400;

        return deleteComplexBenchmark(booksBatchNumber, librariesBatchNumber, personsBatchNumber);
    }
}
