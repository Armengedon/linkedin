package com.ub.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//@Entity
//@Table(name="Enterprise")
//@EntityListeners(AuditingEntityListener.class)
public class Enterprise {
	
	private Set<User> employees;
	private Set<JobOffer> jobOffers;
	
}
