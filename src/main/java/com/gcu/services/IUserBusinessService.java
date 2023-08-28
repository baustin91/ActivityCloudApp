package com.gcu.services;



import com.gcu.models.UserModel;

public interface IUserBusinessService {

	public UserModel getByUsername(String username);
	public boolean addUser(UserModel user);
}
