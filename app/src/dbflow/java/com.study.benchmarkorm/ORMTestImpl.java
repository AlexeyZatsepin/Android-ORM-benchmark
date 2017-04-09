package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

/**
 * Created by v.naminas on 4/6/2017.
 */

public class ORMTestImpl extends ORMTest {
    @Override
    public void initDB() {

    }

    @Override
    public void writeSimple(List<Book> books) {

    }

    @Override
    public void readSimple(int booksQuantity) {

    }

    @Override
    public void updateSimple(List<Book> books) {

    }

    @Override
    public void deleteSimple(int objectsQuantity) {

    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {

    }

    @Override
    public void readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {

    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {

    }

    @Override
    public void deleteComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {

    }
}
