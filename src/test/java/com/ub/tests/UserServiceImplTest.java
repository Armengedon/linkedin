package com.ub.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ub.model.AppUser;
import com.ub.service.UserServiceImpl;

public class UserServiceImplTest {
	
	private UserServiceImpl userServImpl;
	private AppUser appUser;

	@Before
	public void setUp() throws Exception {
		UserServiceImpl userServImpl = new UserServiceImpl();
		
		appUser = new AppUser();
		appUser.setFirstName("Josep");
		appUser.setSecondName("Rovira");
		appUser.setId(10);
		appUser.setEmail("josep@josepSA.com");
		appUser.setPassword("123456");
		
	}

	@Test
	public void testSave() {
		UserServiceImpl userServImpl = new UserServiceImpl();
		AppUser appUser2 = new AppUser();
		
		appUser2 = userServImpl.save(appUser);
		assertEquals("josep@josepSA.com", appUser2.getEmail());
	}

	@Test
	public void testFindByEmail() {
		userServImpl.save(appUser);
		
		AppUser appUser2;
		appUser2 = userServImpl.findByEmail("josep@josepSA.com");
		assertEquals("josep@josepSA.com", appUser2.getEmail());
	}

}
