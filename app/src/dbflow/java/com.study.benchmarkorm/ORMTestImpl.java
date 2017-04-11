package com.study.benchmarkorm;

import android.util.Log;
import android.util.Pair;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.runtime.DBBatchSaveQueue;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.naminas on 4/6/2017.
 */

public class ORMTestImpl extends ORMTest {
    @Override
    public void initDB() {

    }

    @Override
    public void writeSimple(final List<Book> books) {
        FlowManager.getDatabase(LibrariesDB.class)
                .executeTransaction(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (Book book: books) {
                            book.save();
                        }
                    }
                });
//        DBBatchSaveQueue saveQueue = FlowManager.getDatabase(LibrariesDB.class)
//                .getTransactionManager()
//                .getSaveQueue();
//        saveQueue.addAll(new ArrayList<Model>(books));

    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        List<Book> books = new Select().from(Book.class).limit(booksQuantity).queryList();
        for (Book book: books) {
            String name = book.toString();
        }
        return books;
    }

    @Override
    public void updateSimple(List<Book> books) {
        writeSimple(books);
    }

    @Override
    public void deleteSimple(final List<Book> books) {

        FlowManager.getDatabase(LibrariesDB.class)
                .executeTransaction(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (Book book: books) {
                            book.delete();
                        }
                    }
                });
    }

    @Override
    public void writeComplex(final List<Library> libraries, final List<Book> books, final List<Person> persons) {
        FlowManager.getDatabase(LibrariesDB.class)
                .executeTransaction(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (Library library: libraries) {
                            library.save();
                        }
                        for (Book book: books) {
                            book.save();
                        }
                        for (Person person: persons) {
                            person.save();
                        }
                    }
                });
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        List<Library> libraries = new Select().from(Library.class).limit(librariesQuantity).queryList();
        for (Library library: libraries) {
            String name = library.toString();
        }
        List<Book> books = new Select().from(Book.class).limit(booksQuantity * librariesQuantity).queryList();
        for (Book book: books) {
            String name =  book.toString();
        }
        List<Person> persons = new Select().from(Person.class).limit(personsQuantity * librariesQuantity).queryList();
        for (Person person: persons) {
            String name = person.toString();
        }
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        writeComplex(libraries, books, persons);
    }

    @Override
    public void deleteComplex(final List<Library> libraries, final List<Book> books, final List<Person> persons) {
        FlowManager.getDatabase(LibrariesDB.class)
                .executeTransaction(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (Book book: books) {
                            book.save();
                        }
                        for (Person person: persons) {
                            person.save();
                        }
                        for (Library library: libraries) {
                            library.save();
                        }
                    }
                });
    }
}
