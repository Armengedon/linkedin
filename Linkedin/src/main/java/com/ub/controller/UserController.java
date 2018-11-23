package com.ub.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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


	@RequestMapping(value = "/updatePersonalInfo", method = RequestMethod.POST)
	public void updatePersonalInfo(@RequestBody Object appUser, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		System.out.println(foundUser.getFirstName()+"NAME");
		Map info = ((Map)appUser);
		Set s = info.keySet();
		for (Object key: s){
			String k = key.toString().replace("[", "").replaceAll("]","");
			switch (k) {
				case "firstName": 
					System.out.println(info.get(key).toString());
					foundUser.setFirstName(info.get(key).toString());
					break;
				case "secondName": foundUser.setSecondName(info.get(key).toString());
					break;
				case "password": //check
					break;
				case "country": foundUser.setCountry(info.get(key).toString());
					break;
				case "email": foundUser.setEmail(info.get(key).toString());
					break;
				case "postalCode": foundUser.setPostalCode(Integer.parseInt(info.get(key).toString()));
					break;
			}
		}

		userRepository.save(foundUser);

	}
	
}
