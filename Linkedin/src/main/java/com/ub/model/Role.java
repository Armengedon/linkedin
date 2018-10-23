package com.ub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * This class provides a database for user roles, every
 * role has an id and a name.
 * @author Jordi
 *
 */
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

}