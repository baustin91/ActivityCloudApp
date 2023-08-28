package com.gcu.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.gcu.models.ToDoMapper;
import com.gcu.models.ToDoModel;

@Repository
public class ToDoDAO implements IToDoDAO{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public ToDoModel getById(int id) {
		List<ToDoModel> results = jdbcTemplate.query("SELECT * FROM TODO WHERE ID = ?", new ToDoMapper(), id);
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}

	@Override
	public List<ToDoModel> getToDos() {
		List<ToDoModel> results = jdbcTemplate.query("SELECT * FROM TODO", new ToDoMapper());
		return results;
	}

	@Override
	public int addOne(ToDoModel newToDo) {
		SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);
		
		simpleInsert.withTableName("TODO").usingGeneratedKeyColumns("ID");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TITLE", newToDo.getTitle());
		parameters.put("DESCRIPTION", newToDo.getDescription());
		parameters.put("DUE_DATE", newToDo.getDueDate());
		
		Number result = simpleInsert.executeAndReturnKey(parameters);
		
		return result.intValue();
	}

	@Override
	public boolean deleteOne(int id) {
		int results = jdbcTemplate.update("DELETE FROM TODO WHERE ID = ?", id);
		
		if (results > 0)
			return true;
		else
			return false;
	}

	@Override
	public ToDoModel updateOne(int id, ToDoModel updateToDo) {
		int results = jdbcTemplate.update("UPDATE TODO SET TITLE = ?, DESCRIPTION = ?, DUE_DATE = ?  WHERE ID = ?", 
				updateToDo.getTitle(),
				updateToDo.getDescription(),
				updateToDo.getDueDate(),
				id
				);
		
		if (results > 0)
			return updateToDo;
		else 
			return null;
	}

}
