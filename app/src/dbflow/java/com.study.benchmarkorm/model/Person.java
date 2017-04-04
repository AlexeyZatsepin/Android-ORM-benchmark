package com.study.benchmarkorm;


import java.util.Date;

public class Person {
    String firstName;
    String secondName;
    Date birthdayDate;
    String gender;
    long phone;

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
