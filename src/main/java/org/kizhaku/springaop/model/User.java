package org.kizhaku.springaop.model;

public class User {
    String id;
    String firstName;
    String lastName;

    public User(String id, String fName, String lName) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
