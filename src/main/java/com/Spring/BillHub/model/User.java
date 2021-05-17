package com.Spring.BillHub.model;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TESTING")       //This tells Hibernate the name of table is called "Testing"
public class User extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //use id as a primary key
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotNull
    @Size(min =2, max=20, message = "First Name should have at least 2 characters")
    @Column(name = "first_name")       //tells Hibernate to create a column called "user_name"
    private String firstName;

    @NotNull
    @Size(min =2, max= 20, message = "Last Name should have at least 2 characters")
    @Column(name = "last_name")      //tells Hibernate to create a column called "first_name"
    private String lastName;

    @NotNull
    @Email
    @Column(name = "email")          //tells Hibernate to create a column called "e-mail"
    private String email;

    @Column(name = "password")       //tells Hibernate to create a column called "pass_word"
    private String password;          //format of password to be discussed later


    @ElementCollection
    private List<Transaction> transactions = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   //Eager: everytime we retrive an user, we retrive his/her role
    @JoinTable(                             //introduce a third table to maintain the relationship between the two tables
            name = "users_roles",           //the name of the third table is called "users_roles"
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),   //foreign key1
            inverseJoinColumns = @JoinColumn (
                    name = "role_id", referencedColumnName = "id"))   //foreign key2
    private Collection<Role> roles;   //One user can have different roles

    // enum for account type -> default to NOT_REGISTERED
    private AccountType accountType = AccountType.NOT_REGISTERED;
    @Column(name = "food")
    private float foodTotal = 0;
    @Column(name = "entertainment")
    private float entertainmentTotal=0;
    @Column(name = "health")
    private float healthTotal=0;
    @Column(name = "travel")
    private float travelTotal=0;
    @Column(name = "other")
    private float otherTotal=0;


    //default constructor
    public User() {
    }

    //constructor to initialize the arguments above
    public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }


    //Setter and getter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<Transaction> getTransactionsList() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        String type = transaction.getType();
        String amount = transaction.getAmount();
        float amountFlt = Float.parseFloat(amount);
        type = type.toLowerCase();
        if (type.equals("entertainment")){
            entertainmentTotal = entertainmentTotal+amountFlt;
            System.out.println(entertainmentTotal);
        }
        if (type.equals("health")){
            healthTotal = healthTotal+amountFlt;
            System.out.println(healthTotal + "added to total");
        }
        if (type.equals("travel")){
            travelTotal = travelTotal+amountFlt;
            System.out.println(travelTotal);
        }
        if(type.equals("other")){
            otherTotal = otherTotal+amountFlt;
            System.out.println(otherTotal);
        }
        if(type.equals("food")){
            foodTotal = foodTotal+amountFlt;
            System.out.println(foodTotal);


        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }


    public void getTransactions(){
        if(transactions.isEmpty()){
            System.out.println("no transactions");
        }
        int i = 0;

    }

    private int findTransaction(long id){
        if(transactions.isEmpty()){
            System.out.println("no transactions");
        }
        int i = 0;
        while( i < transactions.size()){
            if(id == transactions.get(i).getId()){
                return i;
            }
            i++;
        }
        return -1;
    }public void deleteTransaction(long id){
        int index = findTransaction(id);
        String type = transactions.get(index).getType();
        String amount = transactions.get(index).getAmount();
        float amountFlt = Float.parseFloat(amount);
        type = type.toLowerCase();
        if (type.equals("entertainment")){
            entertainmentTotal = entertainmentTotal- amountFlt;
            System.out.println(entertainmentTotal);
        }
        if (type.equals("health")){
            healthTotal = healthTotal-amountFlt;
            System.out.println(healthTotal + "added to total");
        }
        if (type.equals("travel")){
            travelTotal = travelTotal-amountFlt;
            System.out.println(travelTotal);
        }
        if(type.equals("other")){
            otherTotal = otherTotal-amountFlt;
            System.out.println(otherTotal);
        }
        if(type.equals("food")) {
            foodTotal = foodTotal - amountFlt;
            System.out.println(foodTotal);
        }

            if (index == -1){
            System.out.println("transaction not found");}
        transactions.remove(index);
        int i = 0;


    }

    public float getFoodTotal() {
        return foodTotal;
    }

    public float getEntertainmentTotal() {
        return entertainmentTotal;
    }

    public float getHealthTotal() {
        return healthTotal;
    }

    public float getTravelTotal() {
        return travelTotal;
    }

    public float getOtherTotal() {
        return otherTotal;
    }
}
