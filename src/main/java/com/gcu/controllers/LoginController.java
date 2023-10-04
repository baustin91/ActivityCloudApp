package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gcu.models.UserModel;
import com.gcu.services.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserBusinessService service;
	
	
	@GetMapping("/")
	public String displayLogin(Model model) // login page displayed using model
	{
		logger.info("Displaying login page");
		
		model.addAttribute("userModel", new UserModel());
		return "login.html";//return the login.html page
	}
	
	@PostMapping("/processLogin")
	public String processLogin(UserModel user, Model model)
	{
		logger.info("Processing login for user: {}", user.getUsername());
		
		UserModel userFromDb = service.getByUsername(user.getUsername());
		
		if (userFromDb != null && user.getPassword().equals(userFromDb.getPassword())) {
			logger.info("Login successful for user: {}", user.getUsername());
			return "redirect:/ToDoList/";
		}
		else {
			logger.warn("Invalid username or password for user: {}", user.getUsername());
			model.addAttribute("errorMessage", "Invalid username or password.");
			return "login.html";
		}	
	}
	
	@PostMapping("/processRegistration")//mapping for registration
	public String processReg(Model model) 
	{
		logger.info("Processing registration");
		return "redirect:/register/";//redirect to the register page
	}

}
