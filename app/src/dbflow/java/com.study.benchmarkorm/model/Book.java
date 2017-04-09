package com.study.benchmarkorm.model;

public class Book {
    String author;
    String title;
    int pagesCount;
    int bookId;

    public Book() {
    }

    public Book(String author, String title, int pagesCount, int bookId) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
    }
}
