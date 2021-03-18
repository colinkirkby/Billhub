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
            System.out.println("its empty bruh");
        }
        int i = 0;
        while( i < transactions.size()){
            //System.out.println(transactions.get(i).toString());
            i++;
        }
    }

}
