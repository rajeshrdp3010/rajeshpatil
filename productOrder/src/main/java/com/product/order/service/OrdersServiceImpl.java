package com.product.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.product.order.entity.Order;
import com.product.order.entity.OrderDetail;
import com.product.order.entity.Product;
import com.product.order.exception.OrderNotFoundException;
import com.product.order.exception.ProductNotFoundException;
import com.product.order.repository.OrderDetailsRepository;
import com.product.order.repository.OrdersRepository;
import com.product.order.repository.ProductsRepository;
import com.product.order.request.OrderRequestInterface;
import com.product.order.request.UpdateOrderRequest;

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {

	private static Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private ProductsRepository productsRepository;
	
	@Override
	@Transactional
	public List<Order> getAllOrders() {
		return ordersRepository.findAll();
	}
	
	@Override
	@Transactional
	public Order getOrderById(Long orderId) {
		return ordersRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found - " + orderId));
	}
	
	@Override
	@Retryable(maxAttempts=2, include = {ObjectOptimisticLockingFailureException.class, JpaSystemException.class}, backoff=@Backoff(delay = 1000))
	@Transactional
	public Order createOrder(OrderRequestInterface orderRequest) {
		return createOrder(false, orderRequest, null);
	}
	
	@Override
	@Retryable(maxAttempts=2, include = {ObjectOptimisticLockingFailureException.class, JpaSystemException.class}, backoff=@Backoff(delay = 1000))
	@Transactional
	public Order updateOrder(UpdateOrderRequest orderRequest) {
		logger.info("Exceuting updateOrder -  Update Order Info:{}", orderRequest.toString());
		Order savedOrder = null;
		Product savedProduct = productsRepository.findByName(orderRequest.getProductName()).stream().findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found - " + orderRequest.getProductName()));
		logger.info("Fetching Saved Product :" + savedProduct.toString());
		if (savedProduct != null) {
			savedOrder = ordersRepository.findById(orderRequest.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Order not found - " + orderRequest.getOrderId()));
			logger.info("Fetching Saved Order :" + savedOrder.toString());
			if (orderRequest.getProductName().equals(savedOrder.getOrderDetail().getProduct().getName())) {
				logger.info("Product Name in the updated order is not changed...");
				if (savedOrder.getOrderDetail().getQuantity().compareTo(orderRequest.getQuantity()) > 0) {
					Long diff = savedOrder.getOrderDetail().getQuantity() - orderRequest.getQuantity();
					savedProduct.setQuantity(savedProduct.getQuantity() + diff);
					logger.info("Previous saved order quantity is greater than updated order quantity.Product Quantity is updated : " + savedProduct.getQuantity());
				} else if (savedOrder.getOrderDetail().getQuantity().compareTo(orderRequest.getQuantity()) < 0) {
					Long diff = orderRequest.getQuantity() - savedOrder.getOrderDetail().getQuantity();
					savedProduct.setQuantity(savedProduct.getQuantity() - diff);
					logger.info("Previous saved order quantity is less than updated order quantity.Product Quantity is updated : " + savedProduct.getQuantity());
				}
				savedOrder.getOrderDetail().setQuantity(orderRequest.getQuantity());
				savedOrder.setCustomerName(orderRequest.getCustomerName());
				savedOrder.setCustomerAddress(orderRequest.getCustomerAddress());
				savedProduct = productsRepository.save(savedProduct);
				logger.info("Product saved to database with updated quantity" + savedProduct.toString());
				OrderDetail orderDetail = orderDetailsRepository.save(savedOrder.getOrderDetail());
				savedOrder.setOrderDetail(orderDetail);
				savedOrder = ordersRepository.save(savedOrder);
				logger.info("Order saved to database with updated order detail:" + savedOrder.toString());
			} else {
				Product oldProduct = productsRepository.findByName(savedOrder.getOrderDetail().getProduct().getName()).stream().findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found - " + orderRequest.getProductName()));
				oldProduct.setQuantity(oldProduct.getQuantity() + savedOrder.getOrderDetail().getQuantity());
				oldProduct = productsRepository.save(oldProduct);
				logger.info("Product saved to database with updated quantity" + oldProduct.toString());
				savedOrder = createOrder(true, orderRequest, orderRequest.getOrderId());
				logger.info("Order saved to database with updated order detail:" + savedOrder.toString());
			}
		}
		return savedOrder;
	}
	
	private Order createOrder(boolean isUpdate, OrderRequestInterface orderRequest, Long orderId) {
		logger.info("Exceuting createOrder -  isUpdate:{} orderId:{}...", isUpdate, orderId);
		logger.info("Order Request Info ::" + orderRequest.toString());
		Order order = null;
		Product product = productsRepository.findByName(orderRequest.getProductName()).stream().findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found - " + orderRequest.getProductName()));
		logger.info("Fetching Saved Product :" + product.toString());
		if (product != null) {
			if (product.getQuantity().compareTo(orderRequest.getQuantity()) > 0) {
				logger.info("Requested Product is available in inventory..");
				product.setQuantity(product.getQuantity() - orderRequest.getQuantity());
				product = productsRepository.save(product);
				logger.info("Product saved to database with updated quantity" + product.toString());
				OrderDetail orderDetail = null;
				if (isUpdate) {
					Order savedOrder = ordersRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found - " + orderId));
					logger.info("Fetching Saved Order :" + savedOrder.toString());
					orderDetail = savedOrder.getOrderDetail();
					orderDetail.setQuantity(orderRequest.getQuantity());
					orderDetail.setProduct(product);
					orderDetail = orderDetailsRepository.save(orderDetail);
					logger.info("Order Detail saved to database with updated quantity:" + orderRequest.getQuantity());
					savedOrder.setCustomerName(orderRequest.getCustomerName());
					savedOrder.setCustomerAddress(orderRequest.getCustomerAddress());
					savedOrder.setOrderDetail(orderDetail);
					order = ordersRepository.save(savedOrder);
					logger.info("Order saved to database with updated order detail:" + order.toString());
				} else {
					orderDetail = new OrderDetail(orderRequest.getQuantity(), product);
					orderDetail = orderDetailsRepository.save(orderDetail);
					logger.info("Order Detail saved to database with updated quantity:" + orderRequest.getQuantity());
					order = new Order(orderRequest.getCustomerName(), orderRequest.getCustomerAddress(), orderDetail);
					order = ordersRepository.save(order);
					logger.info("Order saved to database with updated order detail:" + order.toString());
				}
			}
			
		}
		return order;
	}
	
	@Override
	@Retryable(maxAttempts=2, include = {ObjectOptimisticLockingFailureException.class, JpaSystemException.class}, backoff=@Backoff(delay = 1000))
	@Transactional
	public void deleteOrder(Long orderId) {
		logger.info("Exceuting deleteOrder -  orderId:{}", orderId);
		Order savedOrder = ordersRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found - " + orderId));
		if (savedOrder != null) {
			Product product = savedOrder.getOrderDetail().getProduct();
			Product savedProduct = productsRepository.findByName(product.getName()).stream().findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found - " + product.getName()));
			if (savedProduct != null) {
				savedProduct.setQuantity(savedProduct.getQuantity() + savedOrder.getOrderDetail().getQuantity());
				savedProduct = productsRepository.save(savedProduct);
				logger.info("Product saved to database with updated quantity" + savedProduct.toString());
				ordersRepository.deleteById(orderId);
				logger.info("Order deleted successfully:" + orderId);
			}
		}
	}
	
	@Override
	@Transactional
	public void recover(ObjectOptimisticLockingFailureException e) {
		logger.info("Exceuting recover..."+ e.getMessage());
	}
}
