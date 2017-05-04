package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.BookEntityManager;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.LibraryEntityManager;
import com.study.benchmarkorm.model.Person;
import com.study.benchmarkorm.model.PersonEntityManager;

import java.util.List;

public class ORMTestImpl extends ORMTest {
    LibraryEntityManager libraryEntityManager;
    BookEntityManager bookEntityManager;
    PersonEntityManager personEntityManager;

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        libraryEntityManager = new LibraryEntityManager();
        bookEntityManager = new BookEntityManager();
        personEntityManager = new PersonEntityManager();
    }

    @Override
    public void writeSimple(final List<Book> books) {
        bookEntityManager.add(books);
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        return bookEntityManager.select().limit(0, booksQuantity).asList();
    }

    @Override
    public void updateSimple(List<Book> books) {
        bookEntityManager.update(books);
    }

    @Override
    public void deleteSimple(List<Book> books) {
        bookEntityManager.delete(books);
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        libraryEntityManager.add(libraries);
        bookEntityManager.add(books);
        personEntityManager.add(persons);
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        List<Library> libraries = libraryEntityManager.select().limit(0, librariesQuantity).asList();
        List<Book> books = bookEntityManager.select().limit(0, booksQuantity).asList();
        List<Person> persons = personEntityManager.select().limit(0, personsQuantity).asList();
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        libraryEntityManager.update(libraries);
        bookEntityManager.update(books);
        personEntityManager.update(persons);
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        bookEntityManager.delete(books);
        personEntityManager.delete(persons);
        libraryEntityManager.delete(libraries);
    }


}
