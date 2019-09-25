package com.shoppingcart.services;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoppingcart.dao.UserDAO;
import com.shoppingcart.models.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService{

	@Autowired
	private UserDAO userDAO;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDAO.saveUser(user);
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public User getUser(Integer user_id) {
		return userDAO.getUser(user_id);
	}

	@Override
	public Boolean deleteUser(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = userDAO.getUser(userName);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUser_name(), user.getPassword(), getAuthority());	
	}
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public User getUser(String user_name) {
		return userDAO.getUser(user_name);
	}

	@Override
	public User findByUserName(String user_name) {
		return userDAO.getUser(user_name);
	}
}
