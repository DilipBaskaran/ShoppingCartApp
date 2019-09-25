package com.shoppingcart.dao;

import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Cart;
import com.shoppingcart.models.User;

@Repository
public interface CartDAO {

	public boolean saveCart(Cart cart);

	public Cart getCart(Integer cart_id);
	
	public Cart getCart(User user);
	
	public Boolean deleteCart(Integer cart_id);
	
	public Integer getCartSize(String userName);

	public boolean addToCart(Integer productId);

	void updateQuanity(Cart cart, Integer product_id, boolean add, int quantity);

	public boolean reduceCart(int productId);

	public boolean removeCart(int productId);
}
