package com.study.benchmarkorm.model;


import io.realm.RealmObject;

public class Library extends RealmObject{

    private String address;
    private String name;

    public Library() {
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

}
