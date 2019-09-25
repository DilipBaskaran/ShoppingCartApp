package com.shoppingcart.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("2")
@NamedQueries({
	@NamedQuery(name="Book.getBooks",query="SELECT book from Book book"),
	@NamedQuery(name="Book.getBook",query="select book from Book book where book.id=:book_id")
})
public class Book extends Product{

	@NotNull(message="Book Genre cannot be empty")
	private String genre;

	@NotNull(message="Book Author cannot be empty")
	private String author;

	@NotNull(message="Book Publications cannot be empty")
	private String publications;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	public Book(
			@NotBlank(message = "Book Genre cannot be empty") String genre,
			@NotBlank(message = "Book Author cannot be empty") String author,
			@NotBlank(message = "Book Publications cannot be empty") String publications) {
		super();
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}

	public Book() {
		super();
	}

	public Book(
			Integer product_id,
			@NotBlank(message = "Product Name cannot be empty") String prodName,
			@NotBlank(message = "Product Amount cannot be empty") Double price,
			@NotBlank(message = "Book Genre cannot be empty") String genre,
			@NotBlank(message = "Book Author cannot be empty") String author,
			@NotBlank(message = "Book Publications cannot be empty") String publications) {
		super(product_id, prodName, price);
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ ((publications == null) ? 0 : publications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (publications == null) {
			if (other.publications != null)
				return false;
		} else if (!publications.equals(other.publications))
			return false;
		return true;
	}





}
