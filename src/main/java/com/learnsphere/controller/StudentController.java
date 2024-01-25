package com.learnsphere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Users;
import com.learnsphere.services.StudentService;
import com.learnsphere.services.TrainerService;
import com.learnsphere.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	
	@Autowired
	TrainerService trainerService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/pay")
	public String pay(Model model, HttpSession session) {
		
	    List<Course> courses = trainerService.courseList();
	    model.addAttribute("courses", courses);
	    return "pay";
	}

	
	@GetMapping("/myCourses")
	public String viewMyCourses(Model model, HttpSession session) {
		//important
	    String email = (String) session.getAttribute("email");
	    
	    List<Course> myCourses = studentService.getMyCourses(email);
	    model.addAttribute("courses", myCourses);
	    return "myCourses";
	}

}
