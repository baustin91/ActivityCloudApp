package com.gcu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.ToDoModel;
import com.gcu.services.ToDoBusinessService;

@Controller
@RequestMapping("/ToDoList")
public class ToDoListController {
	
	@Autowired
	ToDoBusinessService service;

	@GetMapping("/")
	public String showAllToDoItems(Model model)
	{
		List<ToDoModel> toDoList = service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return "toDoList.html";
	}
	
	@GetMapping("/newToDoForm")
	public String newToDoForm(Model model)
	{
		model.addAttribute("todo", new ToDoModel());
		return "addToDoForm.html";
	}
	
	@PostMapping("/addNew")
	public String addNew(ToDoModel newToDo, BindingResult br, Model model)
	{
		
		service.addToDo(newToDo);
		List<ToDoModel> toDoList =service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return "ToDoList";
	}
	
	@PostMapping("/editToDo")
	public String editToDo(ToDoModel editToDo, Model model)
	{
		model.addAttribute("editToDo", editToDo);
		return "editToDoForm";
	}
	
	@PostMapping("editToDoSubmit")
	public String editToDo(ToDoModel editToDo, BindingResult br, Model model)
	{
		service.updateToDo(editToDo);
		List<ToDoModel> toDoList = service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return"ToDoList";
	}
	
	@PostMapping("/deleteToDo")
	public String deleteToDo(ToDoModel todo, BindingResult br, Model model)
	{
		service.delete(todo);
		List<ToDoModel> toDoList =service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return "ToDoList";
	}
	
}
