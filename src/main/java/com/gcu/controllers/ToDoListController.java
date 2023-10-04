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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/ToDoList")
public class ToDoListController {
	
    private static final Logger logger = LoggerFactory.getLogger(ToDoListController.class);
	
	@Autowired
	ToDoBusinessService service;

	@GetMapping("/")
	public String showAllToDoItems(Model model)
	{
        logger.info("Getting all To-Do items.");
		List<ToDoModel> toDoList = service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return "toDoList.html";
	}
	
	@GetMapping("/newToDoForm")
	public String newToDoForm(Model model)
	{
        logger.info("Displaying new To-Do form.");
		model.addAttribute("todo", new ToDoModel());
		return "addToDoForm.html";
	}
	
	@PostMapping("/addNew")
	public String addNew(ToDoModel newToDo, BindingResult br, Model model)
	{
        logger.info("Adding new To-Do item: {}", newToDo.getTitle());

		service.addToDo(newToDo);
		List<ToDoModel> toDoList =service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return "toDoList.html";
	}
	
	@PostMapping("/editToDo")
	public String editToDo(ToDoModel editToDo, Model model)
	{
        logger.info("Editing To-Do item: {}", editToDo.getTitle());

		model.addAttribute("editToDo", editToDo);
		return "editToDoForm";
	}
	
	@PostMapping("editToDoSubmit")
	public String editToDo(ToDoModel editToDo, BindingResult br, Model model)
	{
		service.updateToDo(editToDo);
		List<ToDoModel> toDoList = service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return"toDoList.html";
	}
	
	@PostMapping("/deleteToDo")
	public String deleteToDo(ToDoModel todo, BindingResult br, Model model)
	{
        logger.info("Deleting To-Do item: {}", todo.getTitle());

		service.delete(todo);
		List<ToDoModel> toDoList =service.getToDos();
		
		model.addAttribute("toDoList", toDoList);
		
		return "toDoList.html";
	}
	
}
