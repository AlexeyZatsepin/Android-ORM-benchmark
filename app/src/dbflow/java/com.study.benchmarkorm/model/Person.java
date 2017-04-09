package com.study.benchmarkorm.model;


import java.util.Date;

public class Person {
    String firstName;
    String secondName;
    Date birthdayDate;
    String gender;
    long phone;

    public Person() {
    }

    public Person(String firstName, String secondName, Date birthdayDate, String gender, long phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
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
