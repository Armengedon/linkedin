package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.AppUser;

public interface JobExperienceRepository extends JpaRepository<AppUser, Long> {

}
