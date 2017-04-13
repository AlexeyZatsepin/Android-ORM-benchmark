package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.dao.BookDao;
import com.study.benchmarkorm.dao.LibraryDao;
import com.study.benchmarkorm.dao.PersonDao;
import com.study.benchmarkorm.db.DatabaseManager;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;


public class ORMTestImpl extends ORMTest{

    private BookDao bookDao;
    private PersonDao personDao;
    private LibraryDao libraryDao;

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        DatabaseManager.init(context);
        bookDao = DatabaseManager.get().getBookDao();
        personDao = DatabaseManager.get().getPersonDao();
        libraryDao = DatabaseManager.get().getLibraryDao();
    }

    @Override
    public void writeSimple(List<Book> books) {
        for (Book book: books){
            bookDao.add(book);
        }
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        return bookDao.getAll();
    }

    @Override
    public void updateSimple(List<Book> books) {
        for (Book book: books){
            bookDao.add(book);
        }
    }

    @Override
    public void deleteSimple(List<Book> books) {
        for (Book book: books){
            bookDao.delete(book);
        }
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book: books){
            bookDao.add(book);
        }
        for (Person person: persons){
            personDao.add(person);
        }
        for (Library library: libraries){
            libraryDao.add(library);
        }
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        return new Pair<>(libraryDao.getAll(), new Pair<>(bookDao.getAll(), personDao.getAll()));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book: books){
            bookDao.update(book);
        }
        for (Person person: persons){
            personDao.update(person);
        }
        for (Library library: libraries){
            libraryDao.update(library);
        }
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book: books){
            bookDao.delete(book);
        }
        for (Person person: persons){
            personDao.delete(person);
        }
        for (Library library:libraries){
            libraryDao.delete(library);
        }
    }
}
