package com.gcu.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.ToDoDAO;
import com.gcu.models.ToDoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ToDoBusinessService implements IToDoBusinessService{
	
    private static final Logger logger = LoggerFactory.getLogger(ToDoBusinessService.class);

	
	@Autowired
	ToDoDAO toDoDAO;

	@Override
	public List<ToDoModel> getToDos() {
        logger.info("Getting all To-Do items.");

		
		List<ToDoModel> toDoList = toDoDAO.getToDos();
		
		return toDoList;
	}
	
	@Override
	public boolean addToDo(ToDoModel todo)
	{
        logger.info("Adding a new To-Do item: {}", todo.getTitle());
		try
		{
			toDoDAO.addOne(todo);
			return true;
		}
		catch (Exception e)
		{
            logger.error("Failed to add To-Do item: {}", e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean updateToDo(ToDoModel todo)
	{
        logger.info("Updating To-Do item with ID: {}", todo.getId());
		try
		{
			toDoDAO.updateOne(todo.getId(), todo);
			return true;
		}
		
		catch(Exception e)
		{
            logger.error("Failed to update To-Do item: {}", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(ToDoModel todo) 
	{
        logger.info("Deleting To-Do item with ID: {}", todo.getId());
		try
		{
			toDoDAO.deleteOne(todo.getId());
		}
		
		catch (Exception e)
		{
            logger.error("Failed to delete To-Do item: {}", e.getMessage());
			return false;
		}
		
		return true;
	}

}
