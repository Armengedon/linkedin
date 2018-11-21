package com.ub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ub.model.AppUser;

import com.ub.model.JobExperience;
import com.ub.model.Publication;
import com.ub.model.Studies;

import com.ub.repository.JobExperienceRepository;
import com.ub.repository.PublicationRepository;
import com.ub.repository.StudiesRepository;
import com.ub.repository.UserRepository;
import com.ub.service.SecurityServiceImpl;
import com.ub.service.UserServiceImpl;

@RestController
@RequestMapping(value ="/users")
public class UserController {
	
	@Autowired
    private UserServiceImpl userService;

    @Autowired
    private SecurityServiceImpl securityService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JobExperienceRepository jobExperiencieRepository;
	
	@Autowired
	private StudiesRepository studiesRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
		
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		model.addAttribute("appUser",  new AppUser());

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createUser(@RequestBody AppUser appUser) {
		appUser.setId(Long.MAX_VALUE);
		String password = appUser.getPassword();
		AppUser savedUser = userService.save(appUser);
		//System.out.println(appUser.getStudies_list().get(0).getBeginYear()+"SAHIDFGASD");
		
        securityService.login(appUser.getEmail(), password);
        
        if (savedUser == null) {
        	return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
        
	}

	@RequestMapping(value = "/register/{phase}",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String updateUser(@RequestBody AppUser appUser , @PathVariable("phase") int phase) {
		
		//Optional<AppUser> userOptional = userRepository.findByEmail(appUser.getEmail());

		AppUser savedUser = null;
		savedUser = userRepository.save(appUser);
		if (savedUser == null) {
			return "403Page";
		} else {
			
			if (phase == 1) {
				return "resgister_1";
			}
			else if (phase ==2) {
				return "resgister_2";
			}
			else if (phase ==3) {
				return "resgister_3";
			}
		}
		return null;
	}
   
	@RequestMapping(value = "/performlogin", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> performlogin(@RequestBody AppUser appUser) {
        try {
        	securityService.login(appUser.getEmail(), appUser.getPassword());
        } catch (AuthenticationException e) {
        	return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
	
	@GetMapping
	public List<AppUser> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public AppUser retrieveUser(@PathVariable long id) {
		Optional<AppUser> foundUser = userRepository.findById(id);

		if (!foundUser.isPresent())
			System.out.println("id-" + id+ " not found");

		return foundUser.get();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody AppUser user, @PathVariable long id) {

		Optional<AppUser> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value = "/addStudies", method = RequestMethod.POST)
	public void addStudies(@RequestBody Studies studies, Principal user) {
		
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		foundUser.addStudies(studies);
		studiesRepository.save(studies);
		userRepository.save(foundUser);
		
	}
	
	@RequestMapping(value = "/addJobExperiencie", method = RequestMethod.POST)
	public void addJobExperience(@RequestBody JobExperience job, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		foundUser.addJobExperience(job);

		jobExperiencieRepository.save(job);	
		userRepository.save(foundUser);
		
	}
	
	@RequestMapping(value = "/addPersonalInfo", method = RequestMethod.POST)
	public void addPersonalInfo(@RequestBody AppUser appUser, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		foundUser.setCountry(appUser.getCountry());
		foundUser.setPostalCode(appUser.getPostalCode());
		userRepository.save(foundUser);
	}
	
	@RequestMapping(value = "/addPublication", method = RequestMethod.POST)
	public void addPublication(@RequestBody Publication p, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		
		foundUser.addPublication(p);
		publicationRepository.save(p);
		
		userRepository.save(foundUser);
	
	}
	
	
	
	
}
