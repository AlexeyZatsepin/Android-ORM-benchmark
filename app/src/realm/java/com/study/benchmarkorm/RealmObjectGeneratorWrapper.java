package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class RealmObjectGeneratorWrapper extends RandomObjectsGenerator{

    Realm realm;

    public RealmObjectGeneratorWrapper(Realm realm) {
        this.realm = realm;
    }

    public RealmList<Book> generateBooks(int quantity) {
        RealmList<Book> books = new RealmList<>();
        for (int i = 0; i < quantity; i++) {
            Book book = nextBook();
            books.add(book);
        }
        return books;
    }

    public RealmList<Person> generatePersons(int quantity) {
        RealmList<Person> persons = new RealmList<>();
        for (int i = 0; i < quantity; i++) {
            Person person = nextPerson();
            persons.add(person);
        }
        return persons;
    }

    public Library nextLibrary(RealmList<Book> books, RealmList<Person> persons) {
        realm.beginTransaction();
        Library library = realm.createObject(Library.class);
        library.setAddress(randomString.nextString());
        library.setName(randomString.nextString());
        library.setBooks(books);
        library.setEmployees(persons);
        realm.commitTransaction();
        return library;
    }

    public Person nextPerson() {
        realm.beginTransaction();
        Person person = realm.createObject(Person.class);
        person.setFirstName(randomString.nextString());
        person.setSecondName(randomString.nextString());
        person.setSecondName(randomString.nextString());
        person.setBirthdayDate(new Date(Math.abs(random.nextLong())));
        person.setGender(random.nextBoolean()? "male": "female");
        person.setPhone(Math.abs(random.nextLong()) % ((int)Math.pow(10, 15)));
        realm.commitTransaction();
        return person;
    }

    public Book nextBook() {
        realm.beginTransaction();
        Book book = realm.createObject(Book.class);
        book.setTitle(randomString.nextString());
        book.setAuthor(randomString.nextString());
        book.setBookId(Math.abs(random.nextInt()) + 1);
        book.setPagesCount(Math.abs(random.nextInt()) + 1);
        realm.commitTransaction();
        return book;
    }


}
