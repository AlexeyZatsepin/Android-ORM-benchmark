package com.study.benchmarkorm;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.onurciner.OHibernate;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

import jsqlite.Exception;

public class ORMTestImpl extends ORMTest {
    private static final String TAG = "OHibernate";
    private OHibernate<Book> bookDao;
    private OHibernate<Person> personDao;
    private OHibernate<Library> libraryDao;

    public ORMTestImpl(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public void initDB(Context context) {
        bookDao = new OHibernate<>(Book.class);
        personDao = new OHibernate<>(Person.class);
        libraryDao = new OHibernate<>(Library.class);
    }

    @Override
    public void writeSimple(List<Book> books) {
        for (Book book : books) {
            try {
                bookDao.insert(book);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

    }

    @Override
    public List<Book> readSimple(int booksQuantity) {
        try {
            bookDao.limit(booksQuantity).selectAll();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    @Override
    public void updateSimple(List<Book> books) {
        for (Book book : books) {
            try {
                bookDao.update(book);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void deleteSimple(List<Book> books) {
        for (Book book : books) {
            try {
                bookDao.delete(book);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void writeComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book : books) {
            try {
                bookDao.insert(book);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        for (Person person : persons) {
            try {
                personDao.insert(person);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        for (Library library : libraries) {
            try {
                libraryDao.insert(library);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public Pair<List<Library>, Pair<List<Book>, List<Person>>> readComplex(int librariesQuantity, int booksQuantity, int personsQuantity) {
        try {
            List<Book> books = bookDao.limit(booksQuantity).selectAll();
            List<Person> persons = personDao.limit(personsQuantity).selectAll();
            List<Library> libraries = libraryDao.limit(librariesQuantity).selectAll();
            return new Pair<>(libraries, new Pair<>(books, persons));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;


    }

    @Override
    public void updateComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book : books) {
            try {
                bookDao.update(book);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        for (Person person : persons) {
            try {
                personDao.update(person);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        for (Library library : libraries) {
            try {
                libraryDao.update(library);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void deleteComplex(List<Library> libraries, List<Book> books, List<Person> persons) {
        for (Book book : books) {
            try {
                bookDao.delete(book);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        for (Person person : persons) {
            try {
                personDao.delete(person);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        for (Library library : libraries) {
            try {
                libraryDao.delete(library);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
