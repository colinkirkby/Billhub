package net.java.springboot.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;


@Entity                     //This tells Hibernate to make a table out of this class, to make this class JPA Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))       //This tells Hibernate the name of table is called "user"
public class User {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)   //use id as a primary key
	 private Long id;
     
	 @Column(name = "first_name")       //tells Hibernate to create a column called "user_name"
	 private String firstName;          
	 
	 @Column(name = "last_name")      //tells Hibernate to create a column called "first_name"
     private String lastName;
	 
	 @Column(name = "email")          //tells Hibernate to create a column called "e-mail"
     private String email;
	 
	 @Column(name = "password")       //tells Hibernate to create a column called "pass_word"
     private String password;          //format of password to be discussed later
     
	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   //Eager: everytime we retrive an user, we retrive his/her role
	 @JoinTable(                             //introduce a third table to maintain the relationship between the two tables
			 name = "users_roles",           //the name of the third table is called "users_roles"
			 joinColumns = @JoinColumn(
					 name = "user_id", referencedColumnName = "id"),   //foreign key1
			 inverseJoinColumns = @JoinColumn (
					 name = "role_id", referencedColumnName = "id"))   //foreign key2
	 
	 private Collection<Role> roles;   //One user can have different roles
     
	 //default constructor
	 public User() {}
     
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

}
