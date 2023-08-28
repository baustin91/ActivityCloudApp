package com.gcu.models;


import java.time.LocalDate;


public class ToDoModel {
	
	private int id;
	private String title;
	private String description;
	private LocalDate dueDate;
	
	
	public ToDoModel(int id, String title, String description, LocalDate dueDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
	}


	public ToDoModel() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
}
