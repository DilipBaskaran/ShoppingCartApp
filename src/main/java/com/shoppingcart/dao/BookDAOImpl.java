package com.shoppingcart.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Book;
import com.shoppingcart.models.Product;

@Repository("bookDAO")
public class BookDAOImpl implements BookDAO {

	Logger logger = Logger.getLogger(BookDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveBook(Book book) {
		boolean saveFlag = true;
		try{
			Product product = book;
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(product);
			session.saveOrUpdate(book);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
		
	}

	@Override
	public List<Book> getBooks() {
		List<Book>  bookList = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createNamedQuery("Book.getBooks",Book.class);
			bookList = (List<Book>)query.getResultList();
		}catch(Exception e){
			logger.error(e.getStackTrace());
			return null;
		}
		return bookList;	}

	@Override
	public Book getBook(Integer book_id) {

		Book book = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createNamedQuery("Book.getBook",Book.class);
			query.setParameter("book_id", book_id);
			book = (Book) (!query.getResultList().isEmpty()?query.getResultList().get(0):null);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			return null;
		}
		return book;

	}

	@Override
	public Boolean deleteBook(Integer book_id) {
		boolean deleteFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			Book book = session.load(Book.class, book_id);
			session.delete(book);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			deleteFlag = false;
		}

		return deleteFlag;
	}

}
