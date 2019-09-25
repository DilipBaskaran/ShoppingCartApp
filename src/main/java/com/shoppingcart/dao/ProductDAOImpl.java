package com.shoppingcart.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Product;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	Logger logger = Logger.getLogger(ProductDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveProduct(Product product) {
		boolean saveFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(product);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public List<Product> getProducts() {
		List<Product>  productList = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From Product",Product.class);
			productList = (List<Product>)query.getResultList();
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
		return productList;
	}

	@Override
	public Product getProduct(Integer product_id) {
		Product product = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createNamedQuery("Product.getProductById");
			query.setParameter("productid",product_id);
			product = (Product) (!query.getResultList().isEmpty()?query.getResultList().get(0):null);
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
		return product;
	}

	@Override
	public Boolean deleteProduct(Integer product_id) {
		boolean deleteFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			Product product = session.load(Product.class,product_id);
			session.delete(product);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			deleteFlag = false;
		}

		return deleteFlag;
	}

}
