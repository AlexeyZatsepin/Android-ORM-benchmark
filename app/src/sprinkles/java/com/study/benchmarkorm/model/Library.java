package com.study.benchmarkorm.model;

import java.util.List;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("Library")
public class Library extends Model{

    @AutoIncrement
    @Key
    @Column("_id")
    private long id;

    @Column("address")
    private String address;

    @Column("name")
    private String name;

    @Column("employees")
    private List<Person> employees;

    @Column("books")
    private List<Book> books;

    public Library() {
    }

    public Library(long id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }
    public Library(long id, String address, String name, List<Person> employees, List<Book> books) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books =  books;
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
