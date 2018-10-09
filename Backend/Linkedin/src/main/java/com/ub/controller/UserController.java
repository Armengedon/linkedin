package com.ub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ub.model.User;
import com.ub.service.SecurityService;
import com.ub.service.UserService;
import com.ub.validator.UserValidator;

@RestController    
public class UserController {
	
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUserName(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }	
    
    /*@GetMapping(path="/")
	public RedirectView index() {
		return new RedirectView("/index.html");
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable long id) {
		Optional<User> foundUser = userRepository.findById(id);

		if (!foundUser.isPresent())
			System.out.println("id-" + id+ " not found");

		return foundUser.get();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
	*/
}
