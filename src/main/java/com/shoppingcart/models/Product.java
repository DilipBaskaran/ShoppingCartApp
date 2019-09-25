package com.shoppingcart.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type", discriminatorType = DiscriminatorType.INTEGER)
@NamedQueries(
		{@NamedQuery(name = "Product.getProductById", query = "SELECT product FROM Product product where product.product_id = :productid")
		})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer product_id;

	@NotNull(message="Product Name cannot be empty")
	private String prodName;

	@NotNull(message="Product Amount cannot be empty")
	private Double price;

	public Product(){

	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product(
			Integer product_id,
			@NotNull(message = "Product Name cannot be empty") String prodName,
			@NotNull(message = "Product Amount cannot be empty") Double price) {
		super();
		this.product_id = product_id;
		this.prodName = prodName;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", prodName=" + prodName
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((prodName == null) ? 0 : prodName.hashCode());
		result = prime * result
				+ ((product_id == null) ? 0 : product_id.hashCode());
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
		Product other = (Product) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (prodName == null) {
			if (other.prodName != null)
				return false;
		} else if (!prodName.equals(other.prodName))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		return true;
	}


}
