package com.study.benchmarkorm.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "Person")
public class Person extends Model{
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "birthdayDate")
    private Date birthdayDate;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private long phone;

    @Column(name = "library")
    private Library library;

    public Person() {
    }

    public Person(String firstName, String secondName, Date birthdayDate, String gender, long phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
    }
    public Person(String firstName, String secondName, Date birthdayDate, String gender, long phone, Library library) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
        this.library = library;
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
