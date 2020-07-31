package com.product.order.request;

public interface OrderRequestInterface {
	
	String getCustomerName();

	void setCustomerName(String customerName);

	String getCustomerAddress();
	
	void setCustomerAddress(String customerAddress);

	String getProductName();

	void setProductName(String productName);

	Long getQuantity();

	void setQuantity(Long quantity);

}
