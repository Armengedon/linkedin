package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}