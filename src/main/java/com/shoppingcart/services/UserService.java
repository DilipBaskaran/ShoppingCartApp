package com.shoppingcart.services;

import java.util.List;

import com.shoppingcart.models.User;

public interface UserService {

	public boolean saveUser(User user);

	public List<User> getUsers();

	public User getUser(Integer user_id);
	
	public User getUser(String user_name);

	public Boolean deleteUser(Integer user_id);

	public User findByUserName(String user_name);
	
}