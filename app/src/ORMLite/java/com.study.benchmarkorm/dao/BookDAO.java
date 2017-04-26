package com.study.benchmarkorm.dao;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.study.benchmarkorm.model.Book;

import java.sql.SQLException;

public class BookDAO extends BaseDaoImpl<Book, Integer> {

   public BookDAO(ConnectionSource connectionSource,
                     Class<Book> dataClass) throws SQLException {
       super(connectionSource, dataClass);
   }
}