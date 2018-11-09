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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "App_Role", //
uniqueConstraints = { //
        @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
public class Role {
	
	@Id
    @GeneratedValue    
    @Column(name = "Role_Id", nullable = false)
	private long id;
	
    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
    
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
	private Set<AppUser> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public Set<AppUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AppUser> users) {
		this.users = users;
	}
	
}