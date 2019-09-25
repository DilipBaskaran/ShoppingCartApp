package com.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Book;

@Repository
public interface BookDAO {

	public boolean saveBook(Book book);

	public List<Book> getBooks();

	public Book getBook(Integer book_id);

	public Boolean deleteBook(Integer book_id);

}
