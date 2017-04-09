package com.study.benchmarkorm.model;

import java.util.List;

public class Library {
    String address;
    String name;
    List<Person> employees;
    List<Book> books;

    public Library() {
    }

    public Library(String address, String name, List<Person> employees, List<Book> books) {
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books = books;
    }
}
