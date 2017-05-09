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
        bookDao.beginTransaction();
        for (Book book: books){
            bookDao.save(book);
        }
        bookDao.endTransaction();
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        bookDao.beginTransaction();
        List<Book> result =  bookDao.get(booksQuantity);
        bookDao.endTransaction();
        return result;
    }

    @Override
    public void updateSimple(List<Book> books) {
        bookDao.beginTransaction();
        for (Book book: books){
            bookDao.save(book);
        }
        bookDao.endTransaction();
    }

    @Override
    public void deleteSimple(List<Book> books) {
        bookDao.beginTransaction();
        for (Book book: books){
            bookDao.delete(book);
        }
        bookDao.endTransaction();
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        bookDao.beginTransaction();
        for (Library library: libraries){
            libraryDao.save(library);
        }
        for (Book book: books){
            bookDao.save(book);
        }
        for (Person person: persons){
            personDao.save(person);
        }
        bookDao.endTransaction();
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        bookDao.beginTransaction();
        Pair<List<Library>, Pair<List<Book>, List<Person>>> result = new Pair<>(libraryDao.get(librariesQuantity), new Pair<>(bookDao.get(booksQuantity), personDao.get(personsQuantity)));
        bookDao.endTransaction();
        return result;
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        bookDao.beginTransaction();
        for (Book book: books){
            bookDao.update(book);
        }
        for (Person person: persons){
            personDao.update(person);
        }
        for (Library library: libraries){
            libraryDao.update(library);
        }
        bookDao.endTransaction();
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        bookDao.beginTransaction();
        for (Book book: books){
            bookDao.delete(book);
        }
        for (Person person: persons){
            personDao.delete(person);
        }
        for (Library library:libraries){
            libraryDao.delete(library);
        }
        bookDao.endTransaction();
    }

    @Override
    public boolean isEmpty() {
        return bookDao.count()==0 && personDao.count()==0 && libraryDao.count()==0;
    }
}
