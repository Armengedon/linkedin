package com.ub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ub.model.AppUser;
import com.ub.model.Role;
import com.ub.model.UserRole;
import com.ub.repository.RoleRepository;
import com.ub.repository.UserRepository;
import com.ub.repository.UserRoleRepository;
import com.ub.utils.EncrytedPasswordUtils;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

//	@PostMapping("/register")
//	public ResponseEntity<Object> createUser(@RequestBody User user) {
//		User savedUser = userRepository.save(user);
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedUser.getId()).toUri();
//
//		return ResponseEntity.created(location).build();
//	}

	@GetMapping(value = { "", "/" })
	public List<AppUser> retrieveAllUsers() {
		return userRepository.findAll();
	}

	// Show Register page.
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		AppUser appUser = new AppUser();

		model.addAttribute("appUser", appUser);

		return "register";
	}

	// @PostMapping("/register")
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String createUser(@RequestBody AppUser appUser) {
		appUser.setId(Long.MAX_VALUE);
		appUser.setPassword(EncrytedPasswordUtils.encrytePassword(appUser.getPassword()));
		AppUser savedUser = null;
		savedUser = userRepository.save(appUser);
		if (savedUser == null) {
			return "403Page";
		} else {
			Optional<Role> role = roleRepository.findById(2L);
			UserRole userRole = new UserRole();
			userRole.setId(Long.MAX_VALUE);
			userRole.setAppUser(savedUser);
			userRole.setAppRole(role.get());
			userRoleRepository.save(userRole);
			// return "loginPage";
			return "loginPage";
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


	@GetMapping("/{id}")
	public AppUser retrieveUser(@PathVariable long id) {
		Optional<AppUser> foundUser = userRepository.findById(id);

		if (!foundUser.isPresent())
			System.out.println("id-" + id + " not found");

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

}
