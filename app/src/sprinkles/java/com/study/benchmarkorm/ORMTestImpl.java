package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Transaction;


public class ORMTestImpl extends ORMTest {
    public ORMTestImpl(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public void initDB(Context context) {

    }

    @Override
    public void writeSimple(List<Book> books) {
        Transaction t = new Transaction();
        try {
            for (Book book : books) {
                book.save(t);
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        List<Book> result = null;
        Transaction t = new Transaction();
        try {
            try (CursorList<Book> bookList = Query.many(Book.class, "SELECT * FROM Book LIMIT ?",
                    String.valueOf(booksQuantity)).get()) {
                result = bookList.asList();
                for (Book book : result) {
                    book.setLibrary(Query.one(Library.class, "SELECT * FROM Library WHERE _id = ?",
                                String.valueOf(book.getLibId())).get());
                }
                t.setSuccessful(true);
            }
        } finally {
            t.finish();
        }

        return result;
    }

    @Override
    public void updateSimple(List<Book> books) {
        Transaction t = new Transaction();
        try {
            for (Book book : books) {
                book.save(t);
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
    }

    @Override
    public void deleteSimple(List<Book> books) {
        Transaction t = new Transaction();
        try {
            for (Book book : books) {
                book.delete(t);
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        Transaction t = new Transaction();
        try {
            for (Book book : books) {
                book.save(t);
            }
            for (Person person : persons) {
                person.save(t);
            }
            for (Library lib : libraries) {
                lib.save(t);
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        Transaction t = new Transaction();

        List<Book> books = null;
        List<Person> persons = null;
        List<Library> libraries = null;
        try {
            try (CursorList<Book> bookList = Query.many(Book.class, "SELECT * FROM Book LIMIT ?", String.valueOf(booksQuantity)).get()) {
                books = bookList.asList();
            }
            for (Book book : books) {
                book.setLibrary(Query.one(Library.class, "SELECT * FROM Library WHERE _id = ?",
                        String.valueOf(book.getLibId())).get());
            }
            try (CursorList<Person> personList = Query.many(Person.class, "SELECT * FROM Person LIMIT ?", String.valueOf(personsQuantity)).get()) {
                persons = personList.asList();
            }
            for (Person person : persons) {
                person.setLibrary(Query.one(Library.class, "SELECT * FROM Library WHERE _id = ?",
                        String.valueOf(person.getLibId())).get());
            }
            try (CursorList<Library> libraryList = Query.many(Library.class, "SELECT * FROM Library LIMIT ?", String.valueOf(librariesQuantity)).get()) {
                libraries = libraryList.asList();
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        Transaction t = new Transaction();
        try {
            for (Book book : books) {
                book.save(t);
            }
            for (Person person : persons) {
                person.save(t);
            }
            for (Library lib : libraries) {
                lib.save(t);
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        Transaction t = new Transaction();
        try {
            for (Book book : books) {
                book.delete(t);
            }
            for (Person person : persons) {
                person.delete(t);
            }
            for (Library lib : libraries) {
                lib.delete(t);
            }
            t.setSuccessful(true);
        } finally {
            t.finish();
        }
    }
}
