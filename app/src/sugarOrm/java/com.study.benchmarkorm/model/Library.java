package com.study.benchmarkorm.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;


public class Library extends SugarRecord{
    String address;

    String name;

    @Ignore
    List<Person> employees;

    @Ignore
    List<Book> books;

    public Library() {
    }

    public Library(String address, String name, List<Person> employees, List<Book> books) {
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books = books;
    }

    public Library(String address, String name) {
        this.address = address;
        this.name = name;
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
