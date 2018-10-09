package com.ub.service;

import com.ub.model.AppUser;

public interface UserService {
    
	void save(AppUser user);

	AppUser findByUserName(String username);
}