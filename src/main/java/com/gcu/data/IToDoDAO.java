package com.gcu.data;

import java.util.List;

import com.gcu.models.ToDoModel;

public interface IToDoDAO {
	
	public ToDoModel getById(int id);
	public List<ToDoModel> getToDos();
	public int addOne(ToDoModel newToDo);
	public boolean deleteOne(int id);
	public ToDoModel updateOne(int id, ToDoModel updateToDo);

}
