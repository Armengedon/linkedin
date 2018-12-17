package com.ub.controller;

import java.security.Principal;
import java.util.Base64;

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
import com.ub.utils.LevenshteinDistance;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	private LevenshteinDistance lDist = new LevenshteinDistance();
	
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = {"/register_3" }, method = RequestMethod.GET)
    public String register_3(Model model, Principal user) {
    	if (user != null) {
    		return "register_3";
    	}
    	return "welcomePage";
    }

    
    @RequestMapping(value = {"/register_31" }, method = RequestMethod.GET)
    public String register_31(Model model, Principal user) {
    	if (user != null) {
    		return "register_31";
    	}
    	return "welcomePage";
    }

    @RequestMapping(value = {"/register_4" }, method = RequestMethod.GET)
    public String register_4(Model model, Principal user) {
    	if (user != null) {
    		return "register_4";
    	}
    	return "welcomePage";
    }

    @RequestMapping(value = {"/register_5" }, method = RequestMethod.GET)
    public String register_5(Model model, Principal principal) {
    	
    	if (principal != null) {
	        String userName = principal.getName();
	    	
	    	AppUser appUser = userRepository.findByEmail(userName);
	    	
	        model.addAttribute("appUser", appUser);
	    	
	        return "register_5";
    	} else {
    		return "welcomePage";
    	}
    }

    @RequestMapping(value = {"/feed" }, method = RequestMethod.GET)
    public String mainPage(Model model, Principal principal) {
    	String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
    	
        model.addAttribute("appUser", appUser);
		model.addAttribute("userRepository",userRepository);
        
        return "mainPage";
    }

    @RequestMapping(value = {"/search_results" }, method = RequestMethod.GET)
    public String searchPage(Model model, Principal principal) {
        String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
        model.addAttribute("appUser", appUser);

    	model.addAttribute("userRepository",userRepository);
		model.addAttribute("lDist", lDist);
		
        return "search-results";
    }
    
    @RequestMapping(value = {"/mynetwork" }, method = RequestMethod.GET)
    public String networkPage(Model model, Principal principal) {
        String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
        model.addAttribute("appUser", appUser);
        
		model.addAttribute("userRepository",userRepository);
		
        return "networkPage";
    }
    
    @RequestMapping(value = {"/jobs" }, method = RequestMethod.GET)
    public String jobsPage(Model model, Principal user) {
    	if (user != null) {return "wip";}
    	return "welcomePage";
    }
    
    @RequestMapping(value = {"/messaging" }, method = RequestMethod.GET)
    public String messagesPage(Model model, Principal user) {
    	if (user != null) {return "wip";}
    	return "welcomePage";
    }
    
    @RequestMapping(value = {"/notifications" }, method = RequestMethod.GET)
    public String notificationsPage(Model model, Principal user) {
    	if (user != null) {return "wip";}
    	return "welcomePage";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String userPage(Model model, Principal principal) {
        String userName = principal.getName();
    	
    	AppUser appUser = userRepository.findByEmail(userName);
    	
        model.addAttribute("appUser", appUser);

        return "loggedUserProfile";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }

 	
}
