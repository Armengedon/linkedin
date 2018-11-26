package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

	
}
