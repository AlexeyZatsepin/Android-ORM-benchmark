package com.study.benchmarkorm.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Library extends RealmObject{

    @PrimaryKey
    private long id;
    private String address;
    private String name;
    private RealmList<Person> employees;
    private RealmList<Book> books;

    public Library() {
    }

    public Library(long id, String address, String name, RealmList<Person> employees, RealmList<Book> books) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(RealmList<Person> employees) {
        this.employees = employees;
    }

    public RealmList<Book> getBooks() {
        return books;
    }

    public void setBooks(RealmList<Book> books) {
        this.books = books;
    }
}
