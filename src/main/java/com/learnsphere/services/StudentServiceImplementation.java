package com.learnsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Lesson;
import com.learnsphere.entity.Users;
import com.learnsphere.repository.UserRepository;

@Service
public class StudentServiceImplementation implements StudentService{

	@Autowired
	UserRepository userRepository;
	
	public List<Course> getMyCourses(String email) {
        Users user = userRepository.findByEmail(email);
        return user.getCourses();
    }
}
