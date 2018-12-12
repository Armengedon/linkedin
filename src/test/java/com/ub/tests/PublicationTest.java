package com.ub.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ub.model.AppUser;
import com.ub.model.JobFunction;
import com.ub.model.Publication;

public class PublicationTest {
	
	private Publication publi;
	private AppUser appUser;

	@Before
	public void setUp() throws Exception {
		
		publi = new Publication();
		
		appUser = new AppUser();
		appUser.setFirstName("Josep");
		appUser.setSecondName("Rovira");
		appUser.setId(10);
		appUser.setEmail("josep@josepSA.com");
		appUser.setPassword("123456");
		
		publi.setMainText("mainText");
		publi.setUser(appUser);
		publi.setId(100);
		Date dat = new Date((long)324242422);
		publi.setDate(dat);
		
		Hashtable<String, List<String>> comments = null;
		
		publi.setComments(comments);
		
		
//		publi.addComment("josep@josepSA.com", "eieiei");
	}


	@Test
	public void testGetUser() {
		AppUser appPubl = publi.getUser();
		
		assertNotNull(appPubl.getEmail());
	}

	@Test
	public void testGetMainText() {
		assertEquals("mainText", publi.getMainText());
	}

	@Test
	public void testGetDate() {
		assertEquals((long)324242422, publi.getDate().getTime());
	}

	@Test
	public void testGetId() {
		assertEquals(100, publi.getId());
	}

	@Test
	public void testGetComments() {
		Hashtable<String, List<String>> comments = publi.getComments();
		assertNull(comments);
	}

	@Ignore
	public void testGetCommentsUser() {
		Hashtable<String, List<String>> comments = (Hashtable<String, List<String>>) publi.getCommentsUser("josep@josepSA.com");
		assertNull(comments);
	}

}
