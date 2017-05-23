package com.study.benchmarkorm.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;


import com.study.benchmarkorm.db.utils.DateConverter;
import com.study.benchmarkorm.model.Library;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface LibraryDao {

    @Query("SELECT * From Library")
    List<Library> findAll();

    @Query("SELECT * From Library LIMIT :limit")
    List<Library> findAll(int limit);

    @Insert(onConflict = IGNORE)
    void insert(Library library);

    @Delete
    void delete(Library library);

    @Update(onConflict = REPLACE)
    void update(Library library);

    @Query("DELETE FROM Library")
    void deleteAll();
}
