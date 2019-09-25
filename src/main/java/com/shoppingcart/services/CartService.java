package com.shoppingcart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.models.Cart;
import com.shoppingcart.models.User;

@Service("cartService")
public interface CartService {

	public boolean saveCart(Cart cart);

	public List<Cart> getCarts();

	public Cart getCart(Integer cart_id);
	
	public Cart getCart(User user);

	public Boolean deleteCart(Integer cart_id);
	
	public Integer getCartSize(String userName);
	
	public boolean addToCart(Integer productId);

	public boolean reduceCart(int productId);

	public boolean removeCart(int productId);
}