package com.learnsphere.services;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Users;

public interface UserService {
	
	void addUser(Users user);
	boolean checkEmail(String email);
	boolean validate(String email,String password);
	String getUserRole(String email);
	void updateUser(Users user);
	Users getUser(String email);
	
	void addCourse(Users user, Course course);

}
