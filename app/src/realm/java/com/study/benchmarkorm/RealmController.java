package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class RealmController implements TestController{
    protected static String TAG = "Realm";
    protected final Realm realm;

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    public void refresh() {
        realm.refresh();
    }

    public void clearAll(Class<? extends RealmObject> clazz) {
        realm.beginTransaction();
        realm.clear(clazz);
        realm.commitTransaction();
    }

    public RealmResults<?extends RealmObject> getAll(Class<? extends RealmObject> clazz) {
        realm.beginTransaction();
        RealmResults<?extends RealmObject> result = realm.where(clazz).findAll();
        realm.commitTransaction();
        return result;
    }

    public RealmObject getById(Class<? extends RealmObject> clazz, int id) {
        realm.beginTransaction();
        RealmObject obj =  realm.where(clazz).equalTo("id",id).findFirst();
        realm.commitTransaction();
        return obj;
    }

    public boolean isEmpty(Class<? extends RealmObject> clazz){
        realm.beginTransaction();
        boolean res = realm.allObjects(clazz).isEmpty();
        realm.commitTransaction();
        return res;
    }
//    //query example
//    public RealmResults<Book> queryedBooks() {
//
//        return realm.where(Book.class)
//                .contains("author", "Author 0")
//                .or()
//                .contains("title", "Realm")
//                .findAll();
//
//    }

    protected RealmList<Book> getBooks(int count){
        RealmList<Book> books = new RealmList<>();
        String author = "R.Tolkien";
        String title = "Lord of the Ring";
        int pages = 1056;
        Random random = new Random();
        for (int i=1; i<=count;i++){
            int bookId = random.nextInt();
            books.add(new Book(i,author,title,pages,bookId));
        }
        return books;
    }

    protected RealmList<Book> update(RealmList<Book> books){
        String author = "Head First";
        String title = "Java CookBook";
        int pages = 2056;
        for (Book book:books){
            book.setAuthor(author);
            book.setTitle(title);
            book.setPagesCount(pages);
        }
        return books;
    }

}
