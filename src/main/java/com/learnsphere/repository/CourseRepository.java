package com.learnsphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer>{

	List<Course> findAll();

}
