package com.ub.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Company")
public class Company {
	
	@Id
	@GeneratedValue
    @Column(name = "Company_Id", nullable = false)
	private long id;
	
	@OneToMany(mappedBy = "company")
	private Set<JobExperience> experiences = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
	private AppUser owner;
	
//	private Set<JobOffer> jobOffers;

	public Set<JobExperience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<JobExperience> experiences) {
		this.experiences = experiences;
	}

	public void addJobExperience(JobExperience experience) {
		experiences.add(experience);
		experience.setEnterprise(this);
	}
	
	public void removeJobExperience(JobExperience experience) {
		experiences.remove(experience);
	}

	public AppUser getOwner() {
		return owner;
	}

	public void setOwner(AppUser owner) {
		this.owner = owner;
	}
	
}
