package com.study.benchmarkorm.model;

import java.util.Date;

import fr.xebia.android.freezer.annotations.Id;
import fr.xebia.android.freezer.annotations.Model;

@Model
public class Person{
    @Id
    long id;

    String firstName;

    String secondName;

    Date birthdayDate;

    String gender;

    long phone;

    Library library;


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


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
