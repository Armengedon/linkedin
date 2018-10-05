package com.ub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ub.bbdd.UserRepository;
import com.ub.model.User;

@Service
public class UserService {


	
	private UserRepository usuaris;
	
	@Autowired
	public UserService(UserRepository usuaris) {
		this.usuaris = usuaris;
	}
	
	public User save(User user) {
		if (user.getId() != -1) { //Existeix?
			
		}
		return usuaris.save(user);
	}
	
}
