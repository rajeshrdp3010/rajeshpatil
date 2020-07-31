package com.product.order.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class OrderRequest {
	
	@Size(max = 100 , message = "Customer name is restricted to 100 characters")
	@NotBlank(message = "Customer name is required")
	protected String customerName;
	
	@Size(max = 255 , message = "Customer address is restricted to 255 characters")
	@NotBlank(message = "Customer address is required")
	protected String customerAddress;
	
	@Size(max = 100 , message = "Product name is restricted to 100 characters")
	@NotBlank(message = "Product name is required")
	protected String productName;
	
	@NotNull(message = "Quantity is required")
	@Range(min=1, max=50, message = "Quantity is restricted to maximum 50")
	protected Long quantity;


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
