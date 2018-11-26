package com.ub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ub.model.Role;

@Repository
@Transactional 
public interface RoleRepository extends JpaRepository<Role, Long>{
 
	public List<String> findByUsers_UserId(Long userId);
	
}
