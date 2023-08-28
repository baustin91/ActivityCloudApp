package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.UserModel;
import com.gcu.services.UserBusinessService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	UserBusinessService service;
	
	@GetMapping("/")
	public String displayRegistration(Model model) 
	{
		model.addAttribute("newUser", new UserModel());
		return "userRegistration.html";
	}
	
	@PostMapping("/processRegistration")
	public String processRegistration(UserModel newUser, BindingResult br, Model model) 
	{
		service.addUser(newUser);
		
		model.addAttribute("newUser", new UserModel());
		return "redirect:/login/";
	}
}
