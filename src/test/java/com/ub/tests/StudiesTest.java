package com.ub.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ub.model.AppUser;
import com.ub.model.Studies;

public class StudiesTest {
	
	private Studies studies;
	private AppUser appUser;

	@Before
	public void setUp() throws Exception {
		
		studies = new Studies();
		appUser = new AppUser();
		
		studies.setBeginYear(2000);
		studies.setComment("hola hola");
		studies.setEndYear(2002);
		studies.setMark(9);
		studies.setTitle("Enginy informàtic");
		studies.setUniversity("UB");
		
		appUser.setFirstName("Josep");
		appUser.setSecondName("Rovira");
		appUser.setId(10);
		appUser.setEmail("josep@josepSA.com");
		appUser.setPassword("123456");
		
		studies.setUser(appUser);
	}
	
	// 10.1

	@Test
	public void testGetTitle() {
		assertEquals("Enginy informàtic", studies.getTitle());
	}

	@Test
	public void testGetUniversity() {
		assertEquals("UB", studies.getUniversity());
	}

	@Test
	public void testGetMark() {
		assertEquals(9, studies.getMark());
	}

	@Test
	public void testGetComment() {
		assertEquals("hola hola", studies.getComment());
	}


	@Test
	public void testGetBeginYear() {
		assertEquals(2000, studies.getBeginYear());
	}


	@Test
	public void testGetEndYear() {
		assertEquals(2002, studies.getEndYear());
	}

	@Test
	public void testGetUser() {
		AppUser appU = studies.getUser();
		assertEquals("josep@josepSA.com", appU.getEmail());
	}

}
