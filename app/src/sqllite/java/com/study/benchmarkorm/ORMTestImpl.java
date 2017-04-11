package com.study.benchmarkorm;

import android.content.Context;
import android.util.Pair;

import com.study.benchmarkorm.dao.BookDao;
import com.study.benchmarkorm.db.DatabaseManager;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;


public class ORMTestImpl extends ORMTest{

    public ORMTestImpl(Context context) {
        super(context);
    }

    @Override
    public void initDB(Context context) {
        DatabaseManager.init(context);
    }

    @Override
    public void writeSimple(List<Book> books) {
        BookDao dao = DatabaseManager.get().getBookDao();
        for (Book book: books){
            dao.add(book);
        }
    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        BookDao dao = DatabaseManager.get().getBookDao();
        return dao.getAll();
    }

    @Override
    public void updateSimple(List<Book> books) {

    }

    @Override
    public void deleteSimple(List<Book> books) {

    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {

    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        return null;
    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {

    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {

    }
}
