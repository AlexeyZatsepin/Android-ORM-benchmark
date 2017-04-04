package com.study.benchmarkorm.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Library extends RealmObject{

    @PrimaryKey
    private int id;
    private String address;
    private String name;
    private RealmList<Person> employees;
    private RealmList<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
