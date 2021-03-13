package com.Spring.BillHub.model;
import javax.persistence.*;

@Embeddable
public class Transaction{

    private Long id;

    private String email;

    private String name;

    private String type;

    private String date;

    private String amount;

    public Transaction() {

    }

    public Transaction(String email, String name, String type, String date, String amount, Long id) {
        this.email = email;
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.id = id;
    }

    public Transaction(String[] input) {
        email = input[0];
        name = input[1];
        type = input[2];
        date = input[3];
        amount = input[4];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "type = " + type + "\n " +
                "amount " + amount + "\n"+
                "id =" + id;
    }

}
