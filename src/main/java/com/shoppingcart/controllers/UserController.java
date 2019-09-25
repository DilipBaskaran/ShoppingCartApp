package com.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingcart.models.User;
import com.shoppingcart.services.UserService;
import com.shoppingcart.validators.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}

		userService.saveUser(user);
		return "redirect:/home";
	}

}
