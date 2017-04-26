package com.study.benchmarkorm.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.study.benchmarkorm.model.Person;

import java.sql.SQLException;

public class PersonDAO extends BaseDaoImpl<Person, Integer> {
    public PersonDAO(ConnectionSource connectionSource,
                        Class<Person> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
