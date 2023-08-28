package com.gcu.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.ToDoDAO;
import com.gcu.models.ToDoModel;

@Service
public class ToDoBusinessService implements IToDoBusinessService{
	
	@Autowired
	ToDoDAO toDoDAO;

	@Override
	public List<ToDoModel> getToDos() {
		
		List<ToDoModel> toDoList = toDoDAO.getToDos();
		
		return toDoList;
	}
	
	@Override
	public boolean addToDo(ToDoModel todo)
	{
		try
		{
			toDoDAO.addOne(todo);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	@Override
	public boolean updateToDo(ToDoModel todo)
	{
		try
		{
			toDoDAO.updateOne(todo.getId(), todo);
			return true;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(ToDoModel todo) 
	{
		try
		{
			toDoDAO.deleteOne(todo.getId());
		}
		
		catch (Exception e)
		{
			return false;
		}
		
		return true;
	}

}
