package com.learnsphere.services;

import java.util.List;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Lesson;

public interface TrainerService {
	
	public void addCourse(Course course);
	public void saveCourse(Course course);
	public void addLesson(Lesson lesson);
	public Course getCourse(int courseId);
	
	public List<Course> courseList();
	List<Lesson> lessonList();
	List<Lesson> getLessonsByCourseId(int courseId);
}
