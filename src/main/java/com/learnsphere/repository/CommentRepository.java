package com.learnsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer>{

}
