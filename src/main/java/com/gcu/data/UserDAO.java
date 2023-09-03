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
			    
	    List<UserModel> results = jdbcTemplate.query("SELECT * FROM USERS WHERE USERNAME = ?", new UserMapper(), username);
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
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
