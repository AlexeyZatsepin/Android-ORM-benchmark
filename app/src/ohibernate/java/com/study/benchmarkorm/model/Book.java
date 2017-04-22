package com.study.benchmarkorm.model;


import com.onurciner.ohibernate.Entity;
import com.onurciner.ohibernate.Id;

@Entity(TABLE_NAME = "book", TABLE_OPERATION = Entity.TABLE_OPERATION_TYPE.DROP_AND_CREATE)
public class Book{

    @Id(PRIMARY_KEY_AUTOINCREMENT = true)
    private Integer id;

    private String author;

    private String title;

    private int pagesCount;

    private int bookId;

    public Book() {
    }

    public Book(long id,String author,String title, int pagesCount, int bookId) {
        this.id = (int)id;
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
