package com.study.benchmarkorm.model;

import com.onurciner.ohibernate.Entity;
import com.onurciner.ohibernate.Id;
import com.onurciner.ohibernate.OneToMany;

import java.util.List;

@Entity(TABLE_NAME = "library", TABLE_OPERATION = Entity.TABLE_OPERATION_TYPE.DROP_AND_CREATE)
public class Library{

    @Id(PRIMARY_KEY_AUTOINCREMENT = true)
    private Integer id;

    private String address;
    private String name;

    @OneToMany(JoinColumn  = "id")
    private List<Person> employees;

    @OneToMany(JoinColumn  = "id")
    private List<Book> books;

    public Library() {
    }

    public Library(int id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public Library(long id, String address, String name, List<Person> persons, List<Book> books) {
        this.id = (int)id;
        this.address = address;
        this.name = name;
        this.employees = persons;
        this.books = books;
    }

    public long getId() {
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
