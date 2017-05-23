package com.study.benchmarkorm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.study.benchmarkorm.dao.BookDao;
import com.study.benchmarkorm.dao.LibraryDao;
import com.study.benchmarkorm.dao.PersonDao;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;


@Database(entities = {Person.class, Book.class, Library.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PersonDao personModel();
    public abstract BookDao bookModel();
    public abstract LibraryDao libraryModel();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
//            INSTANCE =
//                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
//                    // To simplify the codelab, allow queries on the main thread.
//                    // Don't do this on a real app! See PersistenceBasicSample for an example.
//                    .allowMainThreadQueries()
//                    .build();
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "room.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}