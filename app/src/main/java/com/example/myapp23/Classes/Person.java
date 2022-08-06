package com.example.myapp23.Classes;

public class Person {

    private String personID, personName, personAge;

    public Person() {
    }

    public Person(String personName, String personAge) {
        this.personName = personName;
        this.personAge = personAge;
    }

    public Person(String personID, String personName, String personAge) {
        this.personID = personID;
        this.personName = personName;
        this.personAge = personAge;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAge() {
        return personAge;
    }

    public void setPersonAge(String personAge) {
        this.personAge = personAge;
    }
}
