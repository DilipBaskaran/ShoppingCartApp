package com.shoppingcart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shoppingcart.config.ShoppingCartAppApplication;
import com.shoppingcart.config.WebSecurityConfig;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShoppingCartAppApplication.class)
@Import(value={WebSecurityConfig.class})
public class ShoppingCartAppApplicationTests {

	@Autowired
	private MockMvc mvc; 
	
	@Test
	public void exampleTest() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isOk());
	}

}
