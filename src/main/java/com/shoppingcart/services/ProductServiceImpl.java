package com.shoppingcart.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.models.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Override
	public boolean saveProduct(Product product) {
		return productDAO.saveProduct(product);
	}

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public Product getProduct(Integer product_id) {
		return productDAO.getProduct(product_id);
	}

	@Override
	public Boolean deleteProduct(Integer product_id) {
		return productDAO.deleteProduct(product_id);
	}

}
