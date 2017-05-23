package com.study.benchmarkorm.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Library {
    // Fields can be public or private with getters and setters.
    public @PrimaryKey(autoGenerate = true) long id;
    private String address;
    private String name;

    public Library() {
    }

    @Ignore
    public Library(String address, String name) {
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

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
