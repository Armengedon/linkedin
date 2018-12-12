package com.ub.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ub.controller.MainController;
import com.ub.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest {
	
	@Autowired
	private MainController mainController;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
	}
	
    @Test
    public void controllerInitializedCorrectly() {
        assertThat(mainController).isNotNull();
    }

	@Ignore
	public void testWelcomePage() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister_1() throws Exception {
		this.mockMvc.perform(get("/register"))
         .andExpect(status().isOk());
	}

	@Test
	public void testRegister_2() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister_3() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister_31() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister_4() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister_5() {
		fail("Not yet implemented");
	}

	@Test
	public void testMainPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testNetworkPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testJobsPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testMessagesPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotificationsPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testErrorLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdminPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoginPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogoutSuccessfulPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testAccessDenied() {
		fail("Not yet implemented");
	}

}
