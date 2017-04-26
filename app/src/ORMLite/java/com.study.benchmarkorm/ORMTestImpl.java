package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.study.benchmarkorm.dao.BookDAO;
import com.study.benchmarkorm.dao.LibraryDAO;
import com.study.benchmarkorm.dao.PersonDAO;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class ORMTestImpl extends ORMTest {
    private LibraryDAO libraryDAO;
    private BookDAO bookDAO;
    private PersonDAO personDAO;

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        try {
            libraryDAO = BenchmarkApplication.HelperFactory.getHelper().getLibraryDAO();
            bookDAO = BenchmarkApplication.HelperFactory.getHelper().getBookDAO();
            personDAO = BenchmarkApplication.HelperFactory.getHelper().getPersonDAO();
        } catch (SQLException e) {
            Toast.makeText(context, "Exception in DataBase init. Please, restart the program.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void writeSimple(final List<Book> books) {
        try {
            bookDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Book book: books) {
                        bookDAO.create(book);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        try {
            QueryBuilder<Book, Integer> queryBuilder = bookDAO.queryBuilder();
            queryBuilder.limit((long)booksQuantity);
            PreparedQuery<Book> preparedQuery = queryBuilder.prepare();
            return bookDAO.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateSimple(final List<Book> books) {
        try {
            bookDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Book book: books) {
                        bookDAO.update(book);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSimple(final List<Book> books) {
        try {
            bookDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Book book: books) {
                        bookDAO.delete(book);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeComplex(final List<Library> libraries, final List<Book> books, final List<Person> persons) {
        try {
            libraryDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Library library: libraries) {
                        libraryDAO.create(library);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            bookDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Book book: books) {
                        bookDAO.create(book);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            personDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Person person: persons) {
                        personDAO.create(person);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        List<Library> libraries = null;
        List<Book> books = null;
        List<Person> persons = null;

        try {
            QueryBuilder<Library, Integer> queryBuilder = libraryDAO.queryBuilder();
            queryBuilder.limit((long)librariesQuantity);
            PreparedQuery<Library> preparedQuery = queryBuilder.prepare();
            libraries = libraryDAO.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            QueryBuilder<Book, Integer> queryBuilder = bookDAO.queryBuilder();
            queryBuilder.limit((long)booksQuantity);
            PreparedQuery<Book> preparedQuery = queryBuilder.prepare();
            books = bookDAO.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            QueryBuilder<Person, Integer> queryBuilder = personDAO.queryBuilder();
            queryBuilder.limit((long)personsQuantity);
            PreparedQuery<Person> preparedQuery = queryBuilder.prepare();
            persons = personDAO.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(final List<Library> libraries, final List<Book> books, final List<Person> persons) {
        try {
            libraryDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Library library: libraries) {
                        libraryDAO.update(library);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            bookDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Book book: books) {
                        bookDAO.update(book);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            personDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Person person: persons) {
                        personDAO.update(person);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComplex(final List<Library> libraries, final List<Book> books, final List<Person> persons) {
        try {
            libraryDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Library library: libraries) {
                        libraryDAO.delete(library);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            bookDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Book book: books) {
                        bookDAO.delete(book);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            personDAO.callBatchTasks(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    for (Person person: persons) {
                        personDAO.delete(person);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
