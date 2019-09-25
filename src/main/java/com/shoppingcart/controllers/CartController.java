package com.shoppingcart.controllers;


import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingcart.models.Cart;
import com.shoppingcart.models.CartDetail;
import com.shoppingcart.services.CartService;
import com.shoppingcart.services.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	Logger logger = Logger.getLogger(CartController.class);

	@Autowired
	@Qualifier("cartService")
	CartService cartService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	private String getCurrentUserName(){
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		return currentUserName;
	}

	@RequestMapping(value="/addToCart")
	public String addToCart(@RequestParam("productId") int productId,Model model){
		if(getCurrentUserName()==null){
			logger.info("user not logged in ");
			return "redirect:/login";
		}
		cartService.addToCart(productId);
		model.addAttribute("cartSize", cartService.getCartSize(getCurrentUserName()));
		return "redirect:/home";
	}
	
	@RequestMapping(value="/reduce")
	public String reduceCart(@RequestParam("productId") int productId,Model model){
		if(getCurrentUserName()==null){
			logger.info("user not logged in ");
			return "redirect:/login";
		}
		cartService.reduceCart(productId);
		model.addAttribute("cartSize", cartService.getCartSize(getCurrentUserName()));
		logger.info("Reduced Cart Product count");
		return "redirect:/cart/MyCart";
	}
	@RequestMapping(value="/remove")
	public String removeCart(@RequestParam("productId") int productId,Model model){
		if(getCurrentUserName()==null){
			logger.info("user not logged in ");
			return "redirect:/login";
		}
		cartService.removeCart(productId);
		model.addAttribute("cartSize", cartService.getCartSize(getCurrentUserName()));
		logger.info("Removed Product from Cart");
		return "redirect:/cart/MyCart";
	}
	@RequestMapping(value="/add")
	public String addCart(@RequestParam("productId") int productId,Model model){
		if(getCurrentUserName()==null){
			logger.info("user not logged in ");
			return "redirect:/login";
		}
		cartService.addToCart(productId);
		model.addAttribute("cartSize", cartService.getCartSize(getCurrentUserName()));
		logger.info("Added Product count in Cart");
		return "redirect:/cart/MyCart";
	}
	
	@RequestMapping(value="/MyCart")
	public String myCart(Model model){
		String userName = getCurrentUserName();
		if(userName==null){
			logger.info("user not logged in ");
			return "redirect:/login";
		}
		
		Cart cart = cartService.getCart(userService.getUser(userName));
		Set<CartDetail> cartDetails = cart!=null?cart.getCartDetails():null;
		logger.info("getting Cart details");
		model.addAttribute("cartDetails", cartDetails);
		model.addAttribute("cartAmount", cart!=null?cart.getCartAmount():null);
		model.addAttribute("userName", getCurrentUserName());
		model.addAttribute("cartSize", cartService.getCartSize(userName));
		return "cart";
	}
	
	
}
