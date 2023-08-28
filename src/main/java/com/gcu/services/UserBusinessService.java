package com.gcu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.UserDAO;
import com.gcu.models.UserModel;

@Service
public class UserBusinessService implements IUserBusinessService {
	
	@Autowired
	UserDAO userDAO;

	@Override
	public UserModel getByUsername(String username)
	{
		return userDAO.getByUsername(username);
	}

	@Override
	public boolean addUser(UserModel user) {
		
		try 
		{
			userDAO.addUser(user);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

}
