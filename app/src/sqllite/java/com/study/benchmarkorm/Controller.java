package com.study.benchmarkorm;

import android.provider.ContactsContract;
import android.util.Log;

import com.study.benchmarkorm.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Controller implements TestController{
    private static String TAG = "Controller";

    protected List<Book> getBooks(int count){
        List<Book> books = new ArrayList<>();
        String author = "R.Tolkien";
        String title = "Lord of the Ring";
        int pages = 1056;
        Random random = new Random();
        for (int i=0; i<=count;i++){
            int bookId = random.nextInt();
            books.add(new Book(i,author,title,pages,bookId));
        }
        return books;
    }

    protected List<Book> update(List<Book> books){
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

    @Override
    public long testRead(int instances) {
        long start = System.currentTimeMillis();

        long finish = System.currentTimeMillis();
        Log.v(TAG, "Read all " +(finish - start));
        return finish - start;
    }

    @Override
    public long testWrite(int instances) {
        List<Book> books = getBooks(instances);
        long start = System.currentTimeMillis();
        for (Book book: books){

        }
        long finish = System.currentTimeMillis();
        Log.v(TAG, "Write all " +(finish - start));
        return finish-start;
    }

    @Override
    public long testUpdate(int instances) {
        List<Book> books = update(getBooks(instances));
        long start = System.currentTimeMillis();
        for (Book book : books){
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    @Override
    public long testDelete(int instances) {
        List<Book> books = getBooks(instances);
        long start = System.currentTimeMillis();
        for (Book book : books){

        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
