package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;

public class RealmObjectGeneratorWrapper extends RandomObjectsGenerator{

    long index = 0;

    @Override
    public RealmList<Book> generateBooks(int quantity, Library library) {
        RealmList<Book> books = new RealmList<>();
        for (int i = 0; i < quantity; i++) {
            Book book = nextBook(library);
            book.setId(index++);
            books.add(book);
        }
        return books;
    }

    @Override
    public RealmList<Person> generatePersons(int quantity, Library library) {
        RealmList<Person> persons = new RealmList<>();
        for (int i = 0; i < quantity; i++) {
            Person person = nextPerson(library);
            person.setId(index++);
            persons.add(person);
        }
        return persons;
    }

    @Override
    public Library nextLibrary() {
        return new Library(index++, randomString.nextString(), randomString.nextString());
    }

}
