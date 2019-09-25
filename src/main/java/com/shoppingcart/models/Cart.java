package com.shoppingcart.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
@NamedQuery(name="Cart.getCart",query="select c from Cart c where c.user= :user"),
@NamedQuery(name="Cart.getCartById",query="select c from Cart c where c.cart_id = :cartid")
})
public class Cart {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cart_id;
	
	@NotNull(message="Cart Amount cannot be empty")
	private Double cartAmount;
	
	@NotNull
	@OneToOne
	@NaturalId(mutable=true)
	User user;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<CartDetail> cartDetails;
	

	public Set<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(Set<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public Double getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(Double cartAmount) {
		this.cartAmount = cartAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Cart(){
		
	}

	public Cart(
			Integer cart_id,
			@NotBlank(message = "Cart Amount cannot be empty") Double cartAmount,
			@NotNull User user, Set<CartDetail> cartDetails) {
		super();
		this.cart_id = cart_id;
		this.cartAmount = cartAmount;
		this.user = user;
		this.cartDetails = cartDetails;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", cartAmount=" + cartAmount
				+ ", user=" + user + ", cartDetails=" + cartDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartAmount == null) ? 0 : cartAmount.hashCode());
		result = prime * result
				+ ((cartDetails == null) ? 0 : cartDetails.hashCode());
		result = prime * result + ((cart_id == null) ? 0 : cart_id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Cart other = (Cart) obj;
		if (cartAmount == null) {
			if (other.cartAmount != null)
				return false;
		} else if (!cartAmount.equals(other.cartAmount))
			return false;
		if (cartDetails == null) {
			if (other.cartDetails != null)
				return false;
		} else if (!cartDetails.equals(other.cartDetails))
			return false;
		if (cart_id == null) {
			if (other.cart_id != null)
				return false;
		} else if (!cart_id.equals(other.cart_id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
}
