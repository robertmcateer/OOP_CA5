package com.dkit.oopca5.core;

import java.util.Objects;

public class Student {
    private int caoNumber;  // In the CAO system, cao number is unique identifier for student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters



    //copy constructor
    public Student (Student student){
        this.caoNumber = student.caoNumber;
        this.dateOfBirth = student.dateOfBirth;
        this.password = student.password;

    }


    // Constructor
    public Student(int caoNumber, String dateOfBirth, String password) {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;

    }

    //public boolean verifyLoginCredentials( yyy-mm-dd, password);

    public int getCaoNumber() {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber) {
        this.caoNumber = caoNumber;
    }

    public String getDayOfBirth() {
        return dateOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dateOfBirth = dayOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return caoNumber == student.caoNumber &&
                Objects.equals(dateOfBirth, student.dateOfBirth) &&
                Objects.equals(password, student.password);

    }

    @Override
    public int hashCode() {
        return Objects.hash(caoNumber, dateOfBirth, password);
    }

    @Override
    public String toString() {
        return "Student{" +
                "CaoNumber=" + caoNumber +
                ", DateOfBirth='" + dateOfBirth + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
