package com.study.benchmarkorm.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Book")
public class Book extends Model{
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;
    @Column(name = "pagesCount")
    private int pagesCount;
    @Column(name = "bookId")
    private int bookId;

    @Column(name = "library")
    private Library library;

    public Book() {
    }

    public Book(String author,String title,
            int pagesCount, int bookId) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
    }

    public Book(String author, String title, int pagesCount, int bookId, Library library) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
        this.library = library;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setId(int id) {
        this.bookId = id;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
