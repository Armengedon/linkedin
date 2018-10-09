package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
