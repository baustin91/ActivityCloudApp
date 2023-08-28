package com.gcu.services;

import java.util.List;

import com.gcu.models.ToDoModel;

public interface IToDoBusinessService {
	
	public List<ToDoModel> getToDos();
	public boolean addToDo(ToDoModel todo);
	public boolean updateToDo(ToDoModel todo);
	public boolean delete(ToDoModel todo);

}
