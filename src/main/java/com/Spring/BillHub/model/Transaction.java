package com.Spring.BillHub.model;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id                       //use id as the primary key of this table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "transaction_generator",
            sequenceName = "transaction_sequence",
            initialValue = 1000
    )
    private long id;


    @Column (name = "email")
    private String email;
    @Column (name = "name")
    private String name;
    @Column (name = "type")
    private String type;
    @Column (name = "Date")
    private String date;
    @Column (name = "amount")
    private String amount;

    public Transaction(){
    }
    public Transaction(String[] input) {
        email = input[0];
        name = input[1];
        type = input[2];
        date = input[3];
        amount = input[4];
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "email = " + email + "\n" +
                "name = " + name + " \n" +
                "type = " + type + "\n" +
                "amount " + amount + "\n"+
                "id =" + id;
    }

}
