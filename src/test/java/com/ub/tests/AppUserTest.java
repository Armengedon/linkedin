package com.ub.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ub.model.AppUser;
import com.ub.model.Publication;
import com.ub.model.Role;

public class AppUserTest {

	AppUser appUser = new AppUser();
	Role role = new Role();
	Publication publi = new Publication();
	
	
	@Before
	public void setUp() throws Exception {
		appUser = new AppUser();
		role = new Role();
		publi = new Publication();
		appUser.setFirstName("Josep");
		appUser.setSecondName("Rovira");
		appUser.setId(10);
		appUser.setEmail("josep@josepSA.com");
		appUser.setPassword("123456");
		
		role.setId((long) 111111111);
		role.setRoleName("Becari");
		appUser.addRole(role);
		
		publi.setId((long) 22222222);
		Date dat = new Date((long)324242422);
		publi.setDate(dat);
		publi.setUser(appUser);
		publi.setMainText("hola hola hola");
		
		appUser.addPublication(publi);
				
		appUser.setPostalCode(22222);
		
		appUser.setCountry("Spain");
		
	}

	@Test
	public void testGetFirstName() {
		assertEquals("Josep", appUser.getFirstName());
	}

	@Test
	public void testGetSecondName() {
		assertEquals("Rovira", appUser.getSecondName());
	}

	@Test
	public void testGetId() {
		assertEquals(10, appUser.getId());
	}

	@Test
	public void testGetEmail() {
		assertEquals("josep@josepSA.com", appUser.getEmail());
	}

	@Test
	public void testGetPassword() {
		assertEquals("123456", appUser.getPassword());
	}

	@Test
	public void testGetRoles() {
		Set<Role> setRoles = appUser.getRoles();
		assertNotNull(null, setRoles);
	}

	@Ignore
	public void testRemoveRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExperiences() {
		assertNotNull(appUser.getExperiences());
	}

	@Test
	public void testGetStudies_list() {
		assertNotNull(appUser.getStudies_list());
	}

	@Test
	public void testGetPostalCode() {
		assertEquals(22222 , appUser.getPostalCode());
	}

	@Test
	public void testGetPublication() {
		assertNotNull(appUser.getPublication());
	}

	@Test
	public void testGetCountry() {
		assertEquals("Spain", appUser.getCountry());
	}

}
