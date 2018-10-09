package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    
	AppUser findByUserName(String userName);

}