package com.shoppingcart.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="CartDetail.deleteCartDetail",query="delete from CartDetail c where c.cartdetail_id = :cartdetailid")
public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartdetail_id;

	@NotNull
	@ManyToOne
	private Cart cart;

	@NotNull
	@OneToOne
	Product product;

	@NotNull
	private Integer quantity;

	public Integer getCartdetail_id() {
		return cartdetail_id;
	}

	public void setCartdetail_id(Integer cartdetail_id) {
		this.cartdetail_id = cartdetail_id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CartDetail(Integer cartdetail_id, @NotNull Cart cart,
			@NotNull Product product, @NotNull Integer quantity) {
		super();
		this.cartdetail_id = cartdetail_id;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public CartDetail() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartDetail [cartdetail_id=" + cartdetail_id
				+ ", product=" + product + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartdetail_id == null) ? 0 : cartdetail_id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
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
		CartDetail other = (CartDetail) obj;
		if (cartdetail_id == null) {
			if (other.cartdetail_id != null)
				return false;
		} else if (!cartdetail_id.equals(other.cartdetail_id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	
}
