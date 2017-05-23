package com.study.benchmarkorm.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.study.benchmarkorm.model.Book;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface BookDao {

    @Query("select * from Book where id = :id")
    Book loadBookrById(int id);

    @Query("SELECT * FROM Book")
    List<Book> findAll();

    @Query("SELECT * FROM Book LIMIT :limit")
    List<Book> findAll(int limit);

    @Insert(onConflict = IGNORE)
    void insert(Book book);

    @Update(onConflict = REPLACE)
    void update(Book book);

    @Query("DELETE FROM Book")
    void deleteAll();

    @Delete
    void delete(Book book);
}
