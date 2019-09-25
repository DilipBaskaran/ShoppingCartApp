package com.shoppingcart.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoppingcart.models.Apparel;
import com.shoppingcart.models.Book;
import com.shoppingcart.services.ApparelService;
import com.shoppingcart.services.BookService;
import com.shoppingcart.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	@Autowired
	ApparelService apparelService;
	@Autowired
	BookService bookService;

	@RequestMapping("/addApparel")
	public String apparelAddition(Model model){
		model.addAttribute("apparel", new Apparel());
		return "AddApparel";
	}
	@RequestMapping("/addBook")
	public String bookAddition(Model model){
		model.addAttribute("book", new Book());
		return "AddBook";
	}

	@RequestMapping(value="/addApparelForm",method=RequestMethod.POST)
	public String addApparel( @Validated @ModelAttribute Apparel apparel, final BindingResult result){

		if(result.hasErrors()){
			//System.out.println(uav);
			//System.out.println(apparel + "---"+result.getAllErrors());
			return "AddApparel";
		}else{
			apparelService.saveApparel(apparel);
			return "redirect:/";
		}
	}
	@RequestMapping(value="/addBookForm",method=RequestMethod.POST)
	public String addBook( @Validated @ModelAttribute Book book, final BindingResult result){

		if(result.hasErrors()){
			//System.out.println(uav);
			//System.out.println(book + "---"+result.getAllErrors());
			return "AddBook";
		}else{
			bookService.saveBook(book);
			return "redirect:/";
		}
	}
}
