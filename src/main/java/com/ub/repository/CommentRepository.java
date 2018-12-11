package com.ub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	
}
