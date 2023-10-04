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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserBusinessService implements IUserBusinessService, UserDetailsService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserBusinessService.class);

	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public UserModel getByUsername(String username)
	{
        logger.info("Getting user by username: {}", username);
		return userDAO.getByUsername(username);
	}

	@Override
	public boolean addUser(UserModel user) {
        logger.info("Attempting to add a new user: {}", user.getUsername());
		
		try 
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDAO.addUser(user);
            logger.info("User successfully added: {}", user.getUsername());
			return true;
		}
		catch (Exception e)
		{
            logger.error("Failed to add user: {}", e.getMessage());
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username for authentication: {}", username);
	    UserModel user = getByUsername(username);
	    if (user == null) {
            logger.warn("User not found: {}", username);
	        throw new UsernameNotFoundException("User not found");
	    }
        logger.info("User successfully loaded for authentication: {}", username);
	    return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}


}
