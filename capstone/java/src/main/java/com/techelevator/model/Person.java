package com.techelevator.model;

import java.util.Date;

public class Person {

    private int personId;
    private String name;
    private Date birthday;

    public Person () {
    }

    public Person(int personId, String name, Date birthday) {
        this.personId = personId;
        this.name = name;
        this.birthday = birthday;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
