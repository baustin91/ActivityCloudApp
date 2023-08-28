package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.models.UserMapper;
import com.gcu.models.UserModel;

@Repository
public class UserDAO implements IUserDAO{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public UserModel getByUsername (String username) {
		
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		
	    List<UserModel> users = jdbcTemplate.query(sql, new Object[]{username}, new UserMapper());
	    
	    return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public boolean addUser(UserModel newUser) {
		String sql = "INSERT INTO USERS(ID, USERNAME, PASSWORD) VALUES(?,?,?)";
		try
		{
			jdbcTemplate.update(sql, newUser.getId(),
											newUser.getUsername(),
											newUser.getPassword());
		}
		catch (Exception e)
		{
			return false;
		}
		
		return true;
	}

}
