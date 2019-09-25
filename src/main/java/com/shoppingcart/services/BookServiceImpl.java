package com.shoppingcart.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.dao.BookDAO;
import com.shoppingcart.models.Book;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;

	@Override
	public boolean saveBook(Book book) {
		return bookDAO.saveBook(book);
	}

	@Override
	public List<Book> getBooks() {
		return bookDAO.getBooks();
	}

	@Override
	public Book getBook(Integer book_id) {
		return bookDAO.getBook(book_id);
	}

	@Override
	public Boolean deleteApparel(Integer book_id) {
		return bookDAO.deleteBook(book_id);
	}

}
