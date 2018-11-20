package com.ub.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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


    @ManyToMany(cascade = { 
    	    CascadeType.PERSIST, 
    	    CascadeType.MERGE
    	})
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id" ,referencedColumnName = "Role_Id")
    		)
    private Set<Role> roles = new HashSet<>();
	
    @OneToMany(mappedBy = "user")
    private List<JobExperience> experiences = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Studies> studies_list = new ArrayList<>();
    
    @OneToMany(mappedBy = "author")
    private List<Publication> publications_list = new ArrayList<>();
    
    
    @Column(name = "Photo", length = 64, nullable = true)
	private String photoUser;
    
    
    @Column(name = "Postal_Code", nullable = true)
	private long postalCode;
    
    
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = true)
    private Company company;
    
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
	
	public void addStudies(Studies studies) {
		this.studies_list.add(studies);
		studies.setUser(this);
	}
	
	public void removeStudies(Studies studies) {
		this.studies_list.remove(studies);
	}
	
	public void addJobExperience(JobExperience job) {
		experiences.add(job);
		job.setUser(this);
	}
	
	public void removeJobExperience(JobExperience job) {
		experiences.remove(job);
    }
	
	public void setCompany(Company company) {
        if (company == null) {
            if (this.company != null) {
                this.company.setOwner(null);
            }
        }
        else {
        	company.setOwner(this);
        }
        this.company = company;
    }
	
	public Company getCompany() {
		return company;
	}

	public List<JobExperience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<JobExperience> experiences) {
		this.experiences = experiences;
	}

	public List<Studies> getStudies_list() {
		return studies_list;
	}

	public void setStudies_list(List<Studies> studies_list) {
		this.studies_list = studies_list;
	}
	
	public String getPhotoUser() {
		return photoUser;
	}
 	public void setPhotoUser(String photoUser) {
		this.photoUser = photoUser;
	}
 	public long getPostalCode() {
		return postalCode;
	}
 	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}
 	
 	public List<Publication> getPublication() {
 		return publications_list;
 	}
 	
 	public void addPublication(Publication p) {
 		
 		p.addComment(email, "hey soul sister");
 		
 		publications_list.add(p);
 		
 		System.out.println(publications_list.get(0)+"ASFGUAF");
 	}
	
}