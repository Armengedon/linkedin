package com.ub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Job_Experience")
public class JobExperience {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
	private AppUser user;
	
    @Column(name = "Exp_title", length = 64, nullable = false)
	private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company")
	private Company company;
	
	public AppUser getUser() {
		return user;
	}
	
	public void setUser(AppUser user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Company getEnterprise() {
		return company;
	}

	public void setEnterprise(Company company) {
		this.company = company;
		
	}
	
}
