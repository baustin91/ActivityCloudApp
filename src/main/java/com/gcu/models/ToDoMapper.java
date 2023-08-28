package com.gcu.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

public class ToDoMapper implements RowMapper<ToDoModel> {

	@Override
	public ToDoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
        int id = rs.getInt("ID");
        String title = rs.getString("TITLE");
        String description = rs.getString("DESCRIPTION");

        // Convert java.sql.Date to LocalDate
        java.sql.Date sqlDate = rs.getDate("DUE_DATE");
        LocalDate dueDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

        ToDoModel todo = new ToDoModel(id, title, description, dueDate);
		
        return todo;
	}

}
