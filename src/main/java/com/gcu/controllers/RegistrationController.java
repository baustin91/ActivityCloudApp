package com.gcu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.UserModel;
import com.gcu.services.UserBusinessService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserBusinessService service;
	
	@GetMapping("/")
	public String displayRegistration(Model model) 
	{
		logger.info("Displaying registration page");
		model.addAttribute("newUser", new UserModel());
		return "userRegistration.html";
	}
	
	@PostMapping("/processRegistration")
	public String processRegistration(@Valid UserModel newUser, BindingResult br, Model model) 
	{
	    if (br.hasErrors()) 
	    {
			logger.warn("Validation errors during registration: {}", br.getAllErrors());
	    	model.addAttribute("errorMessage", "Invalid username or password.");
	        return "userRegistration.html";
	    }
		else
		{
			logger.info("Processing registration for user: {}", newUser.getUsername());
			service.addUser(newUser);
			logger.info("Registration successful, redirecting to login page.");
			model.addAttribute("newUser", new UserModel());
			return "redirect:/login/";
		}
	}
}
