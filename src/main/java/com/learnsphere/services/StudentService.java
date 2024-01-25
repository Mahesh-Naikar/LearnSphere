package com.learnsphere.services;

import java.util.List;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Lesson;

public interface StudentService {
	
	Lesson getLesson(int lessonId);
	
	List<Course> getMyCourses(String email);

}
