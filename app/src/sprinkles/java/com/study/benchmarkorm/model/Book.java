package com.study.benchmarkorm.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.ForeignKey;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("Book")
public class Book extends Model{

    @AutoIncrementPrimaryKey
    @Column("_id")
    private long id;

    @Column("author")
    private String author;

    @Column("title")
    private String title;

    @Column("pagesCount")
    private int pagesCount;

    @Column("bookID")
    private int bookId;

    @ForeignKey("Library(_id)")
    @Column("library_id") private long libId;

    public Book() {
    }

    public Book(String author,String title,
                int pagesCount, int bookId, Library library) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
        this.libId = library.getId();
    }

    public long getId() {
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

    public void setId(long id) {
        this.id = id;
    }

    public long getLibId() {
        return libId;
    }

    public void setLibId(long libId) {
        this.libId = libId;
    }

    public Library getLibrary(){
        return Library.map.get(id);
    }
}
