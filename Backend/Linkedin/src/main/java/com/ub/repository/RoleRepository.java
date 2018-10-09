package com.ub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ub.model.Role;

@Repository
@Transactional 
public interface RoleRepository extends JpaRepository<Role, Long>{
 
	@Query("SELECT r.roleName from Role r INNER JOIN UserRole ur ON r.id=ur.role where ur.user.id = :userId ")
    public List<String> getRoleNames(@Param("userId") Long userId);

}
