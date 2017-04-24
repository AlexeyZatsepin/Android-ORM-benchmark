package com.study.benchmarkorm.model;

import java.util.Date;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.ForeignKey;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("Person")
public class Person extends Model {

    @AutoIncrementPrimaryKey @Column("_id")
    private long id;

    @Column("firstName")
    private String firstName;
    @Column("secondName")
    private String secondName;
    @Column("bd")
    private Date birthdayDate;
    @Column("gender")
    private String gender;
    @Column("phone")
    private long phone;

    @ForeignKey("Library(_id)")
    @Column("library_id")
    private long libId;

    public Person() {
    }

    public Person(String firstName, String secondName, Date birthdayDate, String gender, long phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
    }

    public Person(long id, String firstName, String secondName, Date birthdayDate, String gender, long phone) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getLibId() {
        return libId;
    }

    public void setLibId(long libId) {
        this.libId = libId;
    }
}
