package com.Spring.BillHub.model;

import javax.persistence.*;

@Entity                         //To make this class JPA Entity
@Table(name="role")             //To make the name of table "role"
public class Role extends AuditModel{

    @Id                       //use id as the primary key of this table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "role_generator",
            sequenceName = "role_sequence",
            initialValue = 1000
    )
    private Long id;
    private String name;

    //default constructor
    public Role() {}

    //constructor
    public Role(String name) {
        super();
        this.name = name;
    }

    //setter and getter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
