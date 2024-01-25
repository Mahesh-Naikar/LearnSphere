package com.learnsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Users;


public interface UserRepository extends JpaRepository<Users, Integer>{
	
	Users findByEmail(String email);
	
}
