package com.ub.model;

import java.util.Date;
import java.util.Set;

public class JobOffer {
	
	private enum JobType{
		PART_TIME,
		FULL_TIME,
	}
	
	private String jobTitle;
	private AppUser jobAnnouncer;
	private Enterprise enterprise;
	private String location;
	private Date publicationDate;
	private long views;
	private String description;
	private JobExpertiseLevel expertiseLevel;
	private Set<JobSector> sector;
	private JobType jobType;
	private Set<JobFunction> jobFunctions;
	
}
