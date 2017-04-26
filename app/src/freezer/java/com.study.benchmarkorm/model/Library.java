package com.study.benchmarkorm.model;

import java.util.List;

import fr.xebia.android.freezer.annotations.Id;
import fr.xebia.android.freezer.annotations.Model;

@Model
public class Library {
    @Id
    long id;

    String address;

    String name;

    List<Person> employees;

    List<Book> books;

    public Library() {
    }

    public Library(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Library(String address, String name, List<Person> employees, List<Book> books) {
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books = books;
    }

    public Library(long id, String address, String name, List<Person> employees, List<Book> books) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books =  books;
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

    @Override
    public String toString() {
        return "Library{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", books=" + books +
                '}';
    }
}
