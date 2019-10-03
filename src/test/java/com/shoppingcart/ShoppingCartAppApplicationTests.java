package com.shoppingcart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.shoppingcart.config.ShoppingCartAppApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ShoppingCartAppApplication.class})
@WebMvcTest
public class ShoppingCartAppApplicationTests {

	@Autowired
	private MockMvc mvc; 
	
	@Test
	public void exampleTest() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isOk());
	}

}
