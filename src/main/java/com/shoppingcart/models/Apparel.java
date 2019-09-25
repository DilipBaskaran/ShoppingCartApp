package com.shoppingcart.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("1")
public class Apparel extends Product{

	@NotNull(message="Apparel Type cannot be empty")
	private String type;

	@NotNull(message="Apparel Brand cannot be empty")
	private String brand;

	@NotNull(message="Apparel Design cannot be empty")
	private String design;



	public Apparel() {
		super();
	}

	public Apparel(
			@NotBlank(message = "Apparel Type cannot be empty") String type,
			@NotBlank(message = "Apparel Brand cannot be empty") String brand,
			@NotBlank(message = "Apparel Design cannot be empty") String design) {
		super();
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public Apparel(
			Integer product_id,
			@NotBlank(message = "Product Name cannot be empty") String prodName,
			@NotBlank(message = "Product Amount cannot be empty") Double price,
			@NotBlank(message = "Apparel Type cannot be empty") String type,
			@NotBlank(message = "Apparel Brand cannot be empty") String brand,
			@NotBlank(message = "Apparel Design cannot be empty") String design) {
		super(product_id, prodName, price);
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((design == null) ? 0 : design.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Apparel other = (Apparel) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (design == null) {
			if (other.design != null)
				return false;
		} else if (!design.equals(other.design))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}



}
