package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.entity.Users;
import com.learnsphere.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ServiceController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		
		boolean emailStatus=userService.checkEmail(user.getEmail());
		if(emailStatus==false) {
			userService.addUser(user);
			System.out.println("User added Successfully.");
		}
		else {
			System.out.println("E-mail : '"+user.getEmail()+"' is already exists.");
		}
		
		return "home";
		
	}

	@PostMapping("/validate")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
		
		//Important : set the session attribute for email
		session.setAttribute("email", email);
		
		if(userService.validate(email, password)==true) {
			String role=userService.getUserRole(email);
			
			if(role.equals("trainer")) {
				return "trainerHome";
			}
			else{
				return "studentHome";
			}
		}
		else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logut(HttpSession session) {
		session.invalidate();
		return "login";
	}

}
