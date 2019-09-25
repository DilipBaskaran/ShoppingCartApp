package com.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingcart.models.User;

@Repository
public interface UserDAO {

	public boolean saveUser(User user);

	public List<User> getUsers();

	public User getUser(Integer user_id);

	public Boolean deleteUser(Integer user_id);
	
	public User getUser(String userName);


}
