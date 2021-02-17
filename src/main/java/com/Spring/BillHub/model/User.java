package com.Spring.BillHub.model;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class User {

    /*
    *   GenerateredValue -> Field is Auto generated and provided by the DataBase not user
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    // location of user to be stored here
    @OneToOne
    Position position;

    public User() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
