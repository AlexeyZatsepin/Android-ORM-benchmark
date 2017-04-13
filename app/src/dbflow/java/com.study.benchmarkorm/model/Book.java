package com.study.benchmarkorm.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.study.benchmarkorm.LibrariesDB;

@Table(database = LibrariesDB.class)
public class Book extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String author;

    @Column
    String title;

    @Column
    int pagesCount;

    @Column
    int bookId;

    @Column
    @ForeignKey
    Library library;


    public Book() {
    }

    public Book(String author, String title, int pagesCount, int bookId) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
    }

    public Book(long id,String author, String title, int pagesCount, int bookId) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pagesCount=" + pagesCount +
                ", bookId=" + bookId +
                '}';
    }
}
