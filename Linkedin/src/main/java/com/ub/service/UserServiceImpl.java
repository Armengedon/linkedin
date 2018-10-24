package com.ub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ub.model.Role;
import com.ub.model.AppUser;
import com.ub.repository.RoleRepository;
import com.ub.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

	public void save(AppUser user) {
		// TODO Auto-generated method stub
		
	}

	public AppUser findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public AppUser findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    */
}