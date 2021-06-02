/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jspcrud;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jiten
 */
public class User {

    
    
private String admin, firstname,lastname, gender, phonenumber, username, password,email, created_date,blocked_status;
public User(String admin,String firstname,String lastname, String gender,String phonenumber,String username,String password,String email,String created_date,String blocked_status) {
    this.admin=admin;
    this.firstname=firstname;
    this.lastname = lastname;
    this.username = username;
    this.email = email;
    this.gender = gender;
    this.phonenumber=phonenumber;
    this.created_date = created_date;
    this.blocked_status=blocked_status;
    
    
    }

    public User(String admin, String firstname, String lastname, String gender, String phonenumber, String username, String password, String email) {
        this.admin = admin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;
        this.email = email;
    }

public User(){}

    User(String firstname,String lastname, String gender,String phonenumber,String username,String email) {
      this.firstname=firstname;
    this.lastname = lastname;
    this.username = username;
    this.email = email;
    this.gender = gender;
    this.phonenumber=phonenumber;
    }
public  String getAdmin() {
	return admin;
}
public void setAdmin(String admin) {
	this.admin = admin;
}
public String getFirstName() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastName() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getCreatedDate() {
	return created_date;
}
public void setCreatedDate(String created_date) {
	this.created_date = created_date;
}


    public String getBlocked_status() {
        return blocked_status;
    }

    public void setBlocked_status(String blocked_status) {
        this.blocked_status = blocked_status;
    }

}