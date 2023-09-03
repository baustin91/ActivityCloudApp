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

import jakarta.validation.Valid;

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
	public String processRegistration(@Valid UserModel newUser, BindingResult br, Model model) 
	{
	    if (br.hasErrors()) 
	    {
	    	model.addAttribute("errorMessage", "Invalid username or password.");
	        return "userRegistration.html";
	    }
	    else
	    {
		service.addUser(newUser);
		
		model.addAttribute("newUser", new UserModel());
		return "redirect:/login/";
	    }
	}
}
