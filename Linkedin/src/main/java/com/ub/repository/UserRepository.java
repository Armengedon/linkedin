package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ub.model.AppUser;

@Repository
@Transactional 
public interface UserRepository extends JpaRepository<AppUser, Long> {
    
	AppUser findByUserName(String userName);
	
	AppUser findByEmail(String email);
	
}