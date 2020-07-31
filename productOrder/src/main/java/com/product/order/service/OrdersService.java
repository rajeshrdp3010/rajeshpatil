package com.product.order.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.product.order.entity.Order;
import com.product.order.request.OrderRequestInterface;
import com.product.order.request.UpdateOrderRequest;

public interface OrdersService {
	
	public List<Order> getAllOrders();
	
	public Order getOrderById(Long orderId);
	
	public Order createOrder(OrderRequestInterface orderRequest);
	
	public Order updateOrder(UpdateOrderRequest orderRequest);
	
	public void deleteOrder(Long orderId);
	
	public void recover(ObjectOptimisticLockingFailureException e);
}
