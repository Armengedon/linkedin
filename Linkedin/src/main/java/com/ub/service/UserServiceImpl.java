package com.ub.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ub.model.AppUser;
import com.ub.model.Role;
import com.ub.repository.RoleRepository;
import com.ub.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser save(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(roleRepository.findAll()));
        return userRepository.save(user);
    }

	@Override
	public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
	}
}