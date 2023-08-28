package com.gcu.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int id = rs.getInt("ID");
		String userName = rs.getString("USERNAME");
		String password = rs.getString("PASSWORD");
		
		UserModel user = new UserModel(id, userName, password);
		return user;
	}

}
