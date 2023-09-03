package com.gcu.services;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.gcu.data.UserDAO;
import com.gcu.models.UserModel;

@Service
public class UserBusinessService implements IUserBusinessService, UserDetailsService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public UserModel getByUsername(String username)
	{
		return userDAO.getByUsername(username);
	}

	@Override
	public boolean addUser(UserModel user) {
		
		try 
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDAO.addUser(user);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserModel user = getByUsername(username);
	    if (user == null) {
	        throw new UsernameNotFoundException("User not found");
	    }
	    
	    return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}


}
