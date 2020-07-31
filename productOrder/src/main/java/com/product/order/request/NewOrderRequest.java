package com.product.order.request;

public class NewOrderRequest extends OrderRequest implements OrderRequestInterface {
	
	public NewOrderRequest(String customerName,String customerAddress,String productName,Long quantity) {
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.productName = productName;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("NewOrderRequest{");
		sb.append("customerName=").append(customerName);
		sb.append(", customerAddress=").append(customerAddress);
		sb.append(", productName=").append(productName);
		sb.append(", quantity=").append(quantity);
		sb.append("}");
		return sb.toString();
	}
}
