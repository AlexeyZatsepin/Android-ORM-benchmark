package com.study.benchmarkorm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Library{

    @Id
    private Long id;
    private String address;
    private String name;
    
    @Keep public Library(String address, String name) {
        this.address = address;
        this.name = name;
    }

    @Generated(hash = 1634895655)
    public Library(Long id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    @Generated(hash = 423229510)
    public Library() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

    public void setId(long id) {
        this.id = id;
    }
}
