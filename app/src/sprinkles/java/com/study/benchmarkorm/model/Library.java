package com.study.benchmarkorm.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;

import android.util.LongSparseArray;

@Table("Library")
public class Library extends Model{

    @AutoIncrementPrimaryKey
    @Column("_id")
    private long id;

    @Column("address")
    private String address;

    @Column("name")
    private String name;

    public Library() {
    }

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
}
