package com.ub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ub.repository.RoleRepository;
import com.ub.repository.UserRepository;
import com.ub.model.AppUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        AppUser user = userRepository.findByFirstName(firstName);
 
        if (user == null) {
            System.out.println("User not found! " + firstName);
            throw new UsernameNotFoundException("User " + firstName + " was not found in the database");
        }
 
        System.out.println("Found User: " + user);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = roleRepository.findByUsers_UserId(user.getId());
        System.out.println(roleNames);
        System.out.println(roleNames);
        System.out.println(roleNames);
        System.out.println(roleNames);
        System.out.println(roleNames);
        System.out.println(roleNames);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(user.getEmail(), //
                user.getPassword(), grantList);
 
        return userDetails;
    }
}