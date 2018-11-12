package com.ub.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "App_User", //
uniqueConstraints = { //
        @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Email") })
public class AppUser {
	
	@Id
	@GeneratedValue
    @Column(name = "User_Id", nullable = false)
	private long userId;
	
    @Column(name = "First_Name", length = 36, nullable = false)
	private String firstName;
    
    @Column(name = "Second_Name", length = 36, nullable = false)
	private String secondName;
    
    @Column(name = "Encryted_Password", length = 128, nullable = false)
	private String password;
    
    @Column(name = "User_Email", length = 64, nullable = false)
	private String email;

	//email amics

	//Columns
	private String studyCenter;

	//Columns
	private int yearBegin;

	//Columns
	private int yearEnd;

	//Columns
	private String photoUser; //URL

	//Columns
	private int postalCode;


    
    @ManyToMany(cascade = { 
    	    CascadeType.PERSIST, 
    	    CascadeType.MERGE
    	})
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id" ,referencedColumnName = "Role_Id")
    		)
    private Set<Role> roles = new HashSet<>();
	
    public AppUser() {
    }

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
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

	public String getStudyCenter() {
		return studyCenter;
	}

	public void setStudyCenter(String studyCenter) {
		this.studyCenter = studyCenter
	}

	public int getYearBegin() {
		return yearBegin;
	}

	public void setYearBegin(int yearBegin) {
		this.yearBegin = yearBegin;
	}

	public int getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getPhotoUser() {
		return photoUser;
	}

	public void setPhotoUser(String photoUser) {
		this.photoUser = photoUser
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		roles.add(role);
        role.getUsers().add(this);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
    }


}