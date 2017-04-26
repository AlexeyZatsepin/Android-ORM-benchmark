package com.study.benchmarkorm.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;

import java.sql.SQLException;


public class LibraryDAO extends BaseDaoImpl<Library, Integer> {
    public LibraryDAO(ConnectionSource connectionSource,
                      Class<Library> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
