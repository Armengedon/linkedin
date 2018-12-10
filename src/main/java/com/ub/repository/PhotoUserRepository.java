package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.PhotoUser;

public interface PhotoUserRepository extends JpaRepository<PhotoUser, Long> {
	

}
