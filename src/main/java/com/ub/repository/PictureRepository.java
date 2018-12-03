package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.AppUser;
import com.ub.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
	
	Picture findByUser(AppUser user);
	
}
