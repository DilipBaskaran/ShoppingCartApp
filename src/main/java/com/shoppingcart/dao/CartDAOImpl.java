package com.shoppingcart.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Cart;
import com.shoppingcart.models.CartDetail;
import com.shoppingcart.models.Product;
import com.shoppingcart.models.User;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {

	Logger logger = Logger.getLogger(CartDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	UserDAO userDAO;

	@Override
	public boolean saveCart(Cart cart) {
		boolean saveFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(cart);
		}catch(Exception e){
			e.printStackTrace();
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public Cart getCart(Integer cart_id) {
		Cart cart = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createNamedQuery("Cart.getCartById");
			query.setParameter("cartid",cart_id);
			cart = (Cart) (!query.getResultList().isEmpty()?query.getResultList().get(0):null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return cart;
	}
	
	@Override
	public void updateQuanity(Cart cart, Integer product_id, boolean add, int quantity) {
		try{
			Session session = sessionFactory.getCurrentSession();
			if(cart !=null){
				Iterator<CartDetail> iter = cart.getCartDetails().iterator();
				CartDetail cartDetail = null;
				int cartDetailId;
				int quantity_cart=0;
				Product product;
				boolean prodAvailable = false;
				while(iter.hasNext()) {
					cartDetail = iter.next();
					cartDetailId = cartDetail.getCartdetail_id();
					quantity_cart = cartDetail.getQuantity();
					product = cartDetail.getProduct();
					if(product.getProduct_id() == product_id){
						if(add){
							quantity_cart+=quantity;
							cartDetail.setQuantity(quantity_cart);
							cart.setCartAmount(cart.getCartAmount()+(quantity*product.getPrice()));
							session.saveOrUpdate(cartDetail);
							session.saveOrUpdate(cart);
						}else{
							if(quantity>=quantity_cart){
								Set<CartDetail> cartDetails = cart.getCartDetails();
								cartDetails.remove(cartDetail);
								if(cartDetails.size()==0){
									session.delete(cartDetail);
									session.delete(cart);
								}
								else{
									cart.setCartAmount(cart.getCartAmount()-cartDetail.getProduct().getPrice()*cartDetail.getQuantity());
									cart.setCartDetails(cartDetails);
									session.saveOrUpdate(cart);
									session.delete(cartDetail);
								}
							}
							else{
								quantity_cart-=quantity;
								cartDetail.setQuantity(quantity_cart);
								cart.setCartAmount(cart.getCartAmount()-(quantity*product.getPrice()));
								session.saveOrUpdate(cartDetail);
							}
						}
						prodAvailable = true;
						break;
					}					
				} //end of while
				if(!prodAvailable && add){
					CartDetail cartDetail_new = new CartDetail();
					product = productDAO.getProduct(product_id);
					cartDetail_new.setProduct(product);
					cartDetail_new.setQuantity(quantity);
					cart.setCartAmount(cart.getCartAmount()+(product.getPrice()*quantity));
					Set<CartDetail> cartDetails = cart.getCartDetails();
					cartDetails.add(cartDetail_new);
					cart.setCartDetails(cartDetails);
					cartDetail_new.setCart(cart);
					session.saveOrUpdate(cart);
					session.saveOrUpdate(cartDetail_new);
				}
			}else{
				cart = new Cart();
				CartDetail cartDetail = new CartDetail();
				Product product = productDAO.getProduct(product_id);
				//System.out.println("product----"+product);
				cartDetail.setProduct(product);
				cartDetail.setQuantity(quantity);
				cart.setCartAmount(product.getPrice()*quantity);
				User user = userDAO.getUser(getCurrentUserName());
				Set<CartDetail> cartDetails = new HashSet<CartDetail>();
				cartDetails.add(cartDetail);
				cart.setCartDetails(cartDetails);
				cart.setUser(user);
				cartDetail.setCart(cart);
				session.saveOrUpdate(cart);
				session.saveOrUpdate(cartDetail);

			}
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
	}

	private void removeCartDetail(CartDetail cartDetail) {
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createNamedQuery("CartDetail.deleteCartDetail");
			query.setParameter("cartdetailid", cartDetail.getCartdetail_id());
			query.executeUpdate();
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public Cart getCart(User user) {
		Cart cart = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createNamedQuery("Cart.getCart");
			query.setParameter("user",user);
			cart = (Cart) (!query.getResultList().isEmpty()?query.getResultList().get(0):null);
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
		return cart;
	}

	@Override
	public Boolean deleteCart(Integer cart_id) {
		Boolean deleteFlag = false;
		try{
			Session session = sessionFactory.getCurrentSession();
			Cart cartToDelete = session.load(Cart.class, cart_id);
			session.delete(cartToDelete);
			deleteFlag = true;
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
		return deleteFlag;
	}

	@Override
	public Integer getCartSize(String userName) {
		Cart cart = null;
		Integer cartSize = 0;
		try{
			User user = userDAO.getUser(userName);
			cart = getCart(user);
			if(cart!=null){
				Set<CartDetail> cartDetails = cart.getCartDetails();
				for(CartDetail cartDetail : cartDetails){
					cartSize += cartDetail.getQuantity();
				}
			}
		}catch(Exception e){
			logger.error(e.getStackTrace());
			return 0;
		}
		return cartSize;
	}

	@Override
	public boolean addToCart(Integer productId) {
		boolean saveFlag = true;
		try{
			User user = userDAO.getUser(getCurrentUserName());
			Cart cart = getCart(user);
			updateQuanity(cart, productId, true, 1);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
	}
	
	private String getCurrentUserName(){
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		return currentUserName;
	}

	@Override
	public boolean reduceCart(int productId) {
		boolean saveFlag = true;
		try{
			User user = userDAO.getUser(getCurrentUserName());
			Cart cart = getCart(user);
			updateQuanity(cart, productId, false, 1);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public boolean removeCart(int productId) {
		boolean saveFlag = true;
		try{
			User user = userDAO.getUser(getCurrentUserName());
			Cart cart = getCart(user);
			Set<CartDetail> cartDetails = cart.getCartDetails();
			for(CartDetail cartDetail : cartDetails){
				if(cartDetail.getProduct().getProduct_id() == productId)
					updateQuanity(cart, productId, false, cartDetail.getQuantity());
			}
			
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
	}
}

