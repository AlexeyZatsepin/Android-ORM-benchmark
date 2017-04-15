package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.BookDao;
import com.study.benchmarkorm.model.DaoSession;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.LibraryDao;
import com.study.benchmarkorm.model.Person;
import com.study.benchmarkorm.model.PersonDao;

import java.util.List;


class ORMTestImpl extends ORMTest {

    private BookDao bookDao;
    private PersonDao personDao;
    private LibraryDao libraryDao;

    public ORMTestImpl(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public void initDB(Context context) {
        DaoSession daoSession = BenchmarkApplication.getDaoSession();
        bookDao = daoSession.getBookDao();
        personDao = daoSession.getPersonDao();
        libraryDao = daoSession.getLibraryDao();
    }

    @Override
    public void writeSimple(List<Book> books) {
        for (Book book: books){
            bookDao.insert(book);
        }
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        bookDao.detachAll();
        return bookDao.queryBuilder().limit(booksQuantity).orderDesc(BookDao.Properties.Id).build().list();
    }

    @Override
    public void updateSimple(List<Book> books) {
        for (Book book : books){
            bookDao.update(book);
        }
    }

    @Override
    public void deleteSimple(List<Book> books) {
        for (Book book : books){
            bookDao.delete(book);
        }
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book: books){
            bookDao.insert(book);
        }
        for (Person person: persons){
            personDao.insert(person);
        }
        for (Library lib: libraries){
            libraryDao.insert(lib);
        }
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        bookDao.detachAll();
        personDao.detachAll();
        libraryDao.detachAll();
        List<Book> books = bookDao.queryBuilder().limit(booksQuantity).orderDesc(BookDao.Properties.Id).build().list();
        List<Person> persons = personDao.queryBuilder().limit(personsQuantity).orderDesc(PersonDao.Properties.Id).build().list();
        List<Library> libraries = libraryDao.queryBuilder().limit(librariesQuantity).orderDesc(LibraryDao.Properties.Id).build().list();
        return new Pair<>(libraries, new Pair<>(books, persons));
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book: books){
            bookDao.update(book);
        }
        for (Person person: persons){
            personDao.update(person);
        }
        for (Library lib: libraries){
            libraryDao.update(lib); //TODO try updateInTx
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
        for (Library lib: libraries){
            libraryDao.delete(lib);//TODO try deleteInTx
        }
    }
}
