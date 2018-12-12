package com.ub.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ub.controller.UserController;
import com.ub.repository.JobExperienceRepository;
import com.ub.repository.PublicationRepository;
import com.ub.repository.StudiesRepository;
import com.ub.repository.UserRepository;
import com.ub.service.SecurityServiceImpl;
import com.ub.service.UserDetailsServiceImpl;
import com.ub.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@InjectMocks
	private UserController usrCntrl;
	
    @Autowired
    protected WebApplicationContext wac;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private UserServiceImpl userServiceImpl;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
    @MockBean
    private SecurityServiceImpl securityService;

    @MockBean
	private UserRepository userRepository;
	
    @MockBean
	private JobExperienceRepository jobExperienceRepository;
	
    @MockBean
	private StudiesRepository studiesRepository;
	
    @MockBean
	private PublicationRepository publicationRepository;
	
	@Before
	public void setUp() throws Exception { 
//		usrCntrl = new UserController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.usrCntrl).build();
//		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//		this.mockMvC = MockMvcBuilders
//				.annotationConfigSetup(TestContext.class, WebApplicationContext.class)
//		            .build();
		
	}

	@Test
	public void testViewRegister() throws Exception{
			
//			usrCntrl = mock(UserController.class);
			this.mockMvc.perform(get("/register"))
			 	            .andExpect(status().isOk());
			
	}

	@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserAppUserInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPerformlogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserAppUserLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStudies() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddJobExperience() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPersonalInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPublication() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePersonalInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStudies() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateJobExperience() {
		fail("Not yet implemented");
	}

}
