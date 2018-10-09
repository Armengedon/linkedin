package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByUserName(String userName);

}
