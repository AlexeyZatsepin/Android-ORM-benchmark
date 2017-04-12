package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

import io.realm.RealmList;

public class RealmObjectGeneratorWrapper extends RandomObjectsGenerator{
    @Override
    public RealmList<Book> generateBooks(int quantity) {
        RealmList<Book> books = new RealmList<>();
        for (int i = 0; i < quantity; i++) {
            books.add(nextBook());
        }
        return books;
    }

    @Override
    public RealmList<Person> generatePersons(int quantity) {
        RealmList<Person> persons = new RealmList<>();
        for (int i = 0; i < quantity; i++) {
            persons.add(nextPerson());
        }
        return persons;
    }

    @Override
    public Library nextLibrary(List<Book> books, List<Person> persons) {
        return new Library(index++, randomString.nextString(), randomString.nextString(),
                new RealmList<>(persons.toArray(new Person[persons.size()])),
                new RealmList<>(books.toArray(new Book[books.size()])));
    }
}
