package com.gcu.data;



import com.gcu.models.UserModel;

public interface IUserDAO {
	
	public UserModel getByUsername(String username);
	public boolean addUser(UserModel newUser);

}
