package com.ub.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ub.model.AppUser;
import com.ub.model.JobExperience;

public class JobExperienceTest {
	private JobExperience jobE;
	private AppUser appUser;

	@Before
	public void setUp() throws Exception {
		appUser = new AppUser();
		appUser.setFirstName("Josep");
		appUser.setSecondName("Rovira");
		appUser.setId(10);
		appUser.setEmail("josep@josepSA.com");
		appUser.setPassword("123456");
		
		jobE = new JobExperience();
		jobE.setBeginYear(1222);
		jobE.setCompany_Name("ub");
		jobE.setTitle("adfdf");
		jobE.setUser(appUser);
	}

	@Test
	public void testGetUser() {
		AppUser appPubl = jobE.getUser();
		assertNotNull(appPubl.getEmail());
	}

	@Test
	public void testGetTitle() {
		assertEquals("adfdf", jobE.getTitle());
	}

	@Test
	public void testGetBeginYear() {
		assertEquals(1222, jobE.getBeginYear());
	}

	@Test
	public void testGetCompany_Name() {
		assertEquals("ub", jobE.getCompany_Name());
	}

}
