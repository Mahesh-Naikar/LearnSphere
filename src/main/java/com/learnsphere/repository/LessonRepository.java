package com.learnsphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Lesson;


public interface LessonRepository extends JpaRepository<Lesson, Integer>{

	List<Lesson> findAll();

	List<Lesson> findByCourse(Course course);
}
