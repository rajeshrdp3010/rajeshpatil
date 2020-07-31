package com.product.order.request;

public class UpdateOrderRequest extends OrderRequest implements OrderRequestInterface{
	
	private Long orderId;
	
	
	public UpdateOrderRequest(Long orderId,String customerName,String customerAddress,String productName,Long quantity) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.productName = productName;
		this.quantity = quantity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UpdateOrderRequest{");
		sb.append("orderId=").append(orderId);
		sb.append(", customerName=").append(customerName);
		sb.append(", customerAddress=").append(customerAddress);
		sb.append(", productName=").append(productName);
		sb.append(", quantity=").append(quantity);
		sb.append("}");
		return sb.toString();
	}
}
