package com.shoppingcart.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingcart.services.CartService;
import com.shoppingcart.services.ProductService;

@Controller
public class HomeController {
		
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	private String getCurrentUserName(){
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		return currentUserName;
	}
	
	//@RequestMapping(value={"/","/index"})
	public String index(){
		return "index";
	}
	
	@RequestMapping(value={"/","/index","/home"})
	public String home(Model model){
		String userName = getCurrentUserName();
		logger.info("displaying Home page with products");
		model.addAttribute("userName",userName==null?"":userName);
		model.addAttribute("products", productService.getProducts());
		model.addAttribute("cartSize", cartService.getCartSize(getCurrentUserName()));
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
