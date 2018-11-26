package com.ub.service;

import com.ub.model.AppUser;

public interface UserService {
    
	AppUser save(AppUser user);

	AppUser findByEmail(String email);
}