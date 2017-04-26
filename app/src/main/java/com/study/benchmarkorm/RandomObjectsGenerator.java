package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomObjectsGenerator {
    protected static Random random = new Random();
    protected static RandomString randomString = new RandomString(40);

    public Book nextBook(Library library) {
        return new Book(randomString.nextString(), randomString.nextString(),
                Math.abs(random.nextInt()) + 1, Math.abs(random.nextInt()) + 1, library);
    }

    public List<Book> generateBooks(int quantity, Library library) {
        List<Book> books = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            books.add(nextBook(library));
        }
        return books;
    }

    public Person nextPerson(Library library) {
        return new Person(randomString.nextString(), randomString.nextString(),
                new Date(System.currentTimeMillis()), random.nextBoolean()? "male": "female",
                Math.abs(random.nextLong()) % ((int)Math.pow(10, 15)), library);
    }

    public List<Person> generatePersons(int quantity, Library library) {
        List<Person> persons = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            persons.add(nextPerson(library));
        }
        return persons;
    }

    public Library nextLibrary() {
        return new Library(randomString.nextString(), randomString.nextString());
    }

    public String nextString() {
        return randomString.nextString();
    }

    public static class RandomString {

        private static final char[] symbols;

        static {
            StringBuilder tmp = new StringBuilder();
            for (char ch = '0'; ch <= '9'; ++ch)
                tmp.append(ch);
            for (char ch = 'a'; ch <= 'z'; ++ch)
                tmp.append(ch);
            for (char ch = 'A'; ch <= 'Z'; ++ch)
                tmp.append(ch);
            tmp.append(" ");
            symbols = tmp.toString().toCharArray();
        }

        private final Random random = new Random();

        private final int maxStringLength;

        public RandomString(int maxStringLength) {
            this.maxStringLength = maxStringLength;
        }

        public String nextString(int length) {
            if (length < 1) {
                throw new IllegalArgumentException("String length must be greater than 0");
            }
            char[] buf = new char[length];
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = symbols[random.nextInt(symbols.length)];
            return new String(buf);
        }

        public String nextString() {
            return nextString(Math.abs(random.nextInt()) % maxStringLength + 1);
        }
    }
}
