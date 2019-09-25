package com.shoppingcart.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shoppingcart.dao.CartDAO;
import com.shoppingcart.models.Cart;
import com.shoppingcart.models.User;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public boolean saveCart(Cart cart) {
		return cartDAO.saveCart(cart);
	}

	@Override
	public List<Cart> getCarts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCart(Integer cart_id) {
		return cartDAO.getCart(cart_id);
	}

	@Override
	public Cart getCart(User user) {
		return cartDAO.getCart(user);
	}

	@Override
	public Boolean deleteCart(Integer cart_id) {
		return cartDAO.deleteCart(cart_id);
	}

	@Override
	public Integer getCartSize(String userName) {
		return cartDAO.getCartSize(userName);
	}

	@Override
	public boolean addToCart(Integer productId) {
		return cartDAO.addToCart(productId);
	}

	@Override
	public boolean reduceCart(int productId) {
		return cartDAO.reduceCart(productId);
	}

	@Override
	public boolean removeCart(int productId) {
		return cartDAO.removeCart(productId);
		
	}

}
