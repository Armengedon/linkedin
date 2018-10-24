package com.ub.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ub.model.AppUser;
import com.ub.model.Role;
import com.ub.model.UserRole;
import com.ub.repository.RoleRepository;
import com.ub.repository.UserRepository;
import com.ub.repository.UserRoleRepository;
import com.ub.utils.EncrytedPasswordUtils;
import com.ub.utils.WebUtils;

@Controller
public class MainController {
	


	 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
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

    @RequestMapping(value = {"/register_2" }, method = RequestMethod.GET)
    public String register_2(Model model) {
        return "register_2";
    }

    @RequestMapping(value = {"/register_3" }, method = RequestMethod.GET)
    public String register_3(Model model) {
        return "register_3";
    }

    @RequestMapping(value = {"/register_4" }, method = RequestMethod.GET)
    public String register_4(Model model) {
        return "register_4";
    }

    @RequestMapping(value = {"/register_5" }, method = RequestMethod.GET)
    public String register_5(Model model) {
        return "register_5";
    }

    @RequestMapping(value = {"/feed" }, method = RequestMethod.GET)
    public String mainPage(Model model) {
        return "mainPage";
    }

    @RequestMapping(value = {"/mynetwork" }, method = RequestMethod.GET)
    public String networkPage(Model model) {
        return "networkPage";
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
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        return "userInfoPage";
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
