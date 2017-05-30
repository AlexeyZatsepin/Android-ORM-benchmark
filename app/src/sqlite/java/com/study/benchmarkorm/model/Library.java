package com.study.benchmarkorm.model;

import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

public class Library{

    private long id;
    private String address;
    private String name;
    private List<Person> employees;
    private List<Book> books;

    public static LongSparseArray<Library> map = new LongSparseArray<>();

    public Library() {
        this.books = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Library(String address, String name) {
        this.address = address;
        this.name = name;
        this.books = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Library(String address, String name,List<Person> persons, List<Book> books) {
        this.address = address;
        this.name = name;
        this.books = books;
        this.employees = persons;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        map.put(id,this);
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

    public void addPerson(Person person){
        employees.add(person);
    }
    public void addBook(Book book){
        books.add(book);
    }
}
