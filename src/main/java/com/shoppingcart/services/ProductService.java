package com.shoppingcart.services;

import java.util.List;

import com.shoppingcart.models.Product;

public interface ProductService {
	

	public boolean saveProduct(Product product);

	public List<Product> getProducts();

	public Product getProduct(Integer product_id);

	public Boolean deleteProduct(Integer product_id);

}
