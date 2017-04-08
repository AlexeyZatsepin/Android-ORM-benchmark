package com.study.benchmarkorm;

import android.util.Log;

import com.study.benchmarkorm.model.Book;

import io.realm.RealmList;

public class Controller extends RealmController{
    @Override
    public long testRead(int instances) {
        if (isEmpty(Book.class)){
            return 0;
        }
        long start = System.currentTimeMillis();
        getAll(Book.class);
        long finish = System.currentTimeMillis();
        Log.v(TAG, "Read all " +(finish - start));
        return finish - start;
    }

    @Override
    public long testWrite(int instances) {
        if (!isEmpty(Book.class)){
            clearAll(Book.class);
        }
        RealmList<Book> books = getBooks(instances);
        long start = System.currentTimeMillis();
        realm.beginTransaction();
        realm.copyToRealm(books);
        realm.commitTransaction();
        long finish = System.currentTimeMillis();
        Log.v(TAG, "Write all " +(finish - start));
        return finish-start;
    }

    @Override
    public long testUpdate(int instances) {
        RealmList<Book> books = update(getBooks(instances));
        long start = System.currentTimeMillis();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(books);
        realm.commitTransaction();
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    @Override
    public long testDelete(int instances) {
        long start = System.currentTimeMillis();
        clearAll(Book.class);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
