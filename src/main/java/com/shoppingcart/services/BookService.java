package com.shoppingcart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.models.Book;

@Service("bookService")
public interface BookService {

	public boolean saveBook(Book book);

	public List<Book> getBooks();

	public Book getBook(Integer book_id);

	public Boolean deleteApparel(Integer book_id);
}