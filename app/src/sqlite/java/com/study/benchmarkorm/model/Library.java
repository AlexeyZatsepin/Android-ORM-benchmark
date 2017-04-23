package com.study.benchmarkorm.model;

import java.util.List;

public class Library{

    private long id;
    private String address;
    private String name;
    private List<Person> employees;
    private List<Book> books;

    public Library() {
    }

    public Library(long id,String address, String name,List<Person> persons, List<Book> books) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.books = books;
        this.employees = persons;
    }
    public Library(String address, String name,List<Person> persons, List<Book> books) {
        this.address = address;
        this.name = name;
        this.books = books;
        this.employees = persons;
    }

    public Library(long id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
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
    public List<Person> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
