package com.ub.bbdd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ub.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByName(String name);

}
