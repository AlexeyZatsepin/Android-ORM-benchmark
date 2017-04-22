package com.study.benchmarkorm.model;


import com.onurciner.ohibernate.Entity;
import com.onurciner.ohibernate.Id;

import java.util.Date;

@Entity(TABLE_NAME = "person", TABLE_OPERATION = Entity.TABLE_OPERATION_TYPE.DROP_AND_CREATE)
public class Person{

    @Id(PRIMARY_KEY_AUTOINCREMENT = true)
    private Integer id;

    private String firstName;

    private String secondName;

    private Date birthdayDate;

    private String gender;
    private long phone;

    public Person() {
    }

    public Person(long id, String firstName, String secondName, Date birthdayDate, String gender, long phone) {
        this.id = (int)id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
