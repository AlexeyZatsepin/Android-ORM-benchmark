package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.orm.SugarTransactionHelper;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

public class ORMTestImpl extends ORMTest {

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {

    }

    @Override
    public void writeSimple(final List<Book> books) {
//        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
//            @Override
//            public void manipulateInTransaction() {
//                for (Book book: books) {
//                    book.save();
//                }
//            }
//        });
        Book.saveInTx(books);
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        return Book.find(Book.class, null, null, null, null, Integer.toString(booksQuantity));
    }

    @Override
    public void updateSimple(List<Book> books) {
        Book.saveInTx(books);
    }

    @Override
    public void deleteSimple(List<Book> books) {
        Book.deleteInTx(books);
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        Library.saveInTx(libraries);
        Book.saveInTx(books);
        Person.saveInTx(persons);
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        List<Library> libraries = Library.find(Library.class, null, null, null, null, Integer.toString(librariesQuantity));
        List<Book> books = Book.find(Book.class, null, null, null, null, Integer.toString(booksQuantity));
        List<Person> persons = Person.find(Person.class, null, null, null, null, Integer.toString(personsQuantity));
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        Library.saveInTx(libraries);
        Book.saveInTx(books);
        Person.saveInTx(persons);
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        Book.saveInTx(books);
        Person.saveInTx(persons);
        Library.saveInTx(libraries);
    }


}
