package com.product.order.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "product_table")
public class Product extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8411862967137357703L;
	
	@Size(max = 100 , message = "Product name is restricted to 100 characters")
	@NotBlank(message = "Product name is required")
	private String name;
	
	@Size(max = 255 , message = "Product description is restricted to 255 characters")
	@NotBlank(message = "Product description is required")
	private String description;
	
	@NotNull(message = "Quantity is required")
	@Range(min=1, max=5000, message = "Quantity is restricted to maximum 5000")
	private Long quantity;

	protected Product() {
	}

	public Product(Long id,String name, String description, Long quantity) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}
	
	public Product(String name, String description, Long quantity) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if ((object == null) || !(object instanceof Product))
			return false;

		final Product a = (Product) object;

		if (this.getId() != null && a.getId() != null && this.getName() != null && a.getName() != null) {
			return getId().equals(a.getId()) && getName().equals(a.getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Product{");
		sb.append("id=").append(getId());
		sb.append(", name=").append(name);
		sb.append(", description=").append(description);
		sb.append(", quantity=").append(quantity);
		sb.append("}");
		return sb.toString();
	}
	
}
