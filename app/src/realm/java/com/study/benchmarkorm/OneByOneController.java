package com.study.benchmarkorm;

import android.util.Log;

import com.study.benchmarkorm.model.Book;

import io.realm.RealmList;

public class OneByOneController extends RealmController {

    @Override
    public long testRead(int instances) {
        if (isEmpty(Book.class)){
            return 0;
        }
        long start = System.currentTimeMillis();
        for (int i=1; i<=instances;i++) {
            realm.beginTransaction();
            realm.where(Book.class).equalTo("id",i).findFirst();
            realm.commitTransaction();
        }
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
        for (Book book:books){
            realm.beginTransaction();
            realm.copyToRealm(book);
            realm.commitTransaction();
        }
        long finish = System.currentTimeMillis();
        Log.v(TAG, "Write by one " +(finish - start));
        return finish-start;
    }

    @Override
    public long testUpdate(int instances) {
        RealmList<Book> books = update(getBooks(instances));
        long start = System.currentTimeMillis();
        for (Book book:books){
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(book);
            realm.commitTransaction();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    @Override
    public long testDelete(int instances) {
        long start = System.currentTimeMillis();
        for (int i=1; i<=instances;i++) {
            realm.beginTransaction();
            realm.where(Book.class).equalTo("id",i).findFirst().removeFromRealm();
            realm.commitTransaction();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
