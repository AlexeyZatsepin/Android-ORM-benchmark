package com.study.benchmarkorm.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Library.class,
        parentColumns = "id",
        childColumns = "library_id")}, indices = {@Index("library_id")})
public class Book {
    public @PrimaryKey(autoGenerate = true) long id;
    private String author;
    private String title;
    private int pagesCount;
    private int bookId;
    @ColumnInfo(name="library_id")
    private long libraryId;

    public Book() {
    }

    @Ignore
    public Book(String author, String title, int pagesCount, int bookId, Library library) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
        this.libraryId = library.id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    public Library getLibrary(){
        return Library.map.get(libraryId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pagesCount=" + pagesCount +
                ", bookId=" + bookId +
                ", libraryId='" + libraryId + '\'' +
                '}';
    }
}
