package com.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Product;

@Repository
public interface ProductDAO{

	public boolean saveProduct(Product product);

	public List<Product> getProducts();

	public Product getProduct(Integer product_id);

	public Boolean deleteProduct(Integer product_id);
	
}
