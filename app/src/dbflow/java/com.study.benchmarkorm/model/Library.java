package com.study.benchmarkorm.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.study.benchmarkorm.LibrariesDB;

import java.util.List;

@Table(database = LibrariesDB.class)
public class Library extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String address;

    @Column
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
