package com.ub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "App_User", //
uniqueConstraints = { //
        @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser {
	
	@Id
	@GeneratedValue
    @Column(name = "User_Id", nullable = false)
	private long userId;
	
    @Column(name = "User_Name", length = 36, nullable = false)
	private String userName;
    
    @Column(name = "Encryted_Password", length = 128, nullable = false)
	private String password;
    
    @Column(name = "User_Email", length = 64, nullable = false)
	private String email;
	
    public AppUser() {
    }
    
//	public AppUser(String userName, String email, String password) {
//		this.userName = userName;
//		this.password = password;
//		this.email = email;
//	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public long getId() {
		return userId;
	}
	
	public void setId(long id) {
		this.userId = id;
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

}
