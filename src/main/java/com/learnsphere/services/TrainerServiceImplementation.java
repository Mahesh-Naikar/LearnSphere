package com.learnsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Lesson;
import com.learnsphere.repository.CourseRepository;
import com.learnsphere.repository.LessonRepository;

@Service
public class TrainerServiceImplementation implements TrainerService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Override
	public void addCourse(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addLesson(Lesson lesson) {
		lessonRepository.save(lesson);
	}

	@Override
	public Course getCourse(int courseId) {
		return courseRepository.findById(courseId).get();
	}

	@Override
	public List<Course> courseList() {
	    return courseRepository.findAll();
	}
	
	@Override
	public List<Lesson> lessonList() {
	    return lessonRepository.findAll();
	}

	@Override
	public List<Lesson> getLessonsByCourseId(int courseId) {
		Course course=courseRepository.findById(courseId).get();
		return lessonRepository.findByCourse(course);
	}
}
