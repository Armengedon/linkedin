package com.ub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ub.model.AppUser;
import com.ub.repository.UserRepository;
import com.ub.utils.WebUtils;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

	@RequestMapping(value = {"/register_1" }, method = RequestMethod.GET)
    public String register_1(Model model) {
        return "register_1";
    }

    @RequestMapping(value = {"/register_3" }, method = RequestMethod.GET)
    public String register_3(Model model) {
        return "register_3";
    }

    
    @RequestMapping(value = {"/register_31" }, method = RequestMethod.GET)
    public String register_31(Model model) {
        return "register_31";
    }

    @RequestMapping(value = {"/register_4" }, method = RequestMethod.GET)
    public String register_4(Model model) {
        return "register_4";
    }

    @RequestMapping(value = {"/register_5" }, method = RequestMethod.GET)
    public String register_5(Model model, Principal principal) {
        String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
    	
        model.addAttribute("appUser", appUser);
    	
        return "register_5";
    }

    @RequestMapping(value = {"/feed" }, method = RequestMethod.GET)
    public String mainPage(Model model, Principal principal) {
    	String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
    	
        model.addAttribute("appUser", appUser);
    	
        return "mainPage";
    }

    @RequestMapping(value = {"/mynetwork" }, method = RequestMethod.GET)
    public String networkPage(Model model, Principal principal) {
        String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
        model.addAttribute("appUser", appUser);
        
        String email = appUser.getEmail(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		model.addAttribute("userFriends", foundUser.getAppUserFriends(userRepository));
		
        return "networkPage";
    }
    
    @RequestMapping(value = {"/jobs" }, method = RequestMethod.GET)
    public String jobsPage(Model model) {
        return "jobs";
    }
    
    @RequestMapping(value = {"/messaging" }, method = RequestMethod.GET)
    public String messagesPage(Model model) {
        return "messages";
    }
    
    @RequestMapping(value = {"/notifications" }, method = RequestMethod.GET)
    public String notificationsPage(Model model) {
        return "spam";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String userPage(Model model, Principal principal) {
        String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
    	
        model.addAttribute("appUser", appUser);

        return "user";
    }

    @RequestMapping(value = {"/errorLogin" }, method = RequestMethod.GET)
    public String errorLogin(Model model) {
        return "errorLogin";
    }

 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
    
    public String loginPage(Model model) {
 
        return "userInfoPage";
    }
    
    
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
	
	
}
