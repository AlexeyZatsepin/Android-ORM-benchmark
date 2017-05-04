package com.study.benchmarkorm.model;

import java.io.Serializable;
import java.util.List;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.OneToMany;

@Entity
public class AbstractLibrary implements Serializable {
    @Key
    @Generated
    int id;

    String address;

    String name;

//    @OneToMany
    List<Person> employees;

//    @OneToMany
    List<Book> books;

    public AbstractLibrary() {
    }

    public AbstractLibrary(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public AbstractLibrary(String address, String name, List<Person> employees, List<Book> books) {
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.books = books;
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
