package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.UserModel;
import com.gcu.services.UserBusinessService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserBusinessService service;
	
	@GetMapping("/")
	public String displayLogin(Model model) // login page displayed using model
	{
		model.addAttribute("userModel", new UserModel());
		return "login.html";//return the login.html page
	}
	
	@PostMapping("/processLogin")
	public String processLogin(UserModel user, Model model)
	{
		UserModel userFromDb = service.getByUsername(user.getUsername());
		
		if (userFromDb != null && user.getPassword().equals(userFromDb.getPassword()))
			return "redirect:/ToDoList/";
		else 
			return "login.html";
			
	}
	
	@PostMapping("/processRegistration")//mapping for registration
	public String processReg(Model model) 
	{
		return "redirect:/register/";//redirect to the register page
	}

}
