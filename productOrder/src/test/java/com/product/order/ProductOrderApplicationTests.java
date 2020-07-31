package com.product.order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.order.config.ProductOrderConfiguration;
import com.product.order.entity.Order;
import com.product.order.entity.Product;
import com.product.order.request.NewOrderRequest;
import com.product.order.request.UpdateOrderRequest;
import com.product.order.service.OrdersServiceImpl;
import com.product.order.service.ProductsServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ProductOrderApplication.class, ProductOrderConfiguration.class })
@ActiveProfiles("test")
class ProductOrderApplicationTests extends TestCase {

	@Autowired
	private ProductsServiceImpl productsService;

	@Autowired
	private OrdersServiceImpl ordersService;

	@Before
	public void setUp() {

	}

	@Test
	public void testProductsService() {

		Product foundProduct = productsService.getProductByName("Iphone");

		assertNotNull(foundProduct);

		assertEquals("Iphone", foundProduct.getName());
	}

	@Test
	public void testCreateOrderService() {

		Order savedOrder = ordersService.createOrder(new NewOrderRequest("Ted", "1 Broadway", "TV", 1l));
		Order foundOrder = ordersService.getOrderById(savedOrder.getId());

		assertNotNull(foundOrder);

		assertEquals(savedOrder.getId(), foundOrder.getId());

	}

	@Test
	public void testUpdateOrderService() {

		Order savedOrder = ordersService.createOrder(new NewOrderRequest("Ben", "2 greyrock pl", "TV", 1l));
		Order foundOrder = ordersService.getOrderById(savedOrder.getId());

		assertNotNull(foundOrder);

		assertEquals(savedOrder.getId(), foundOrder.getId());

		Order updatedOrder = ordersService
				.updateOrder(new UpdateOrderRequest(savedOrder.getId(), "John", "2 greyrock place", "Iphone", 3l));
		assertNotNull(updatedOrder);

		assertEquals(savedOrder.getId(), updatedOrder.getId());

		assertEquals("Iphone", updatedOrder.getOrderDetail().getProduct().getName());
		assertEquals("John", updatedOrder.getCustomerName());
		assertEquals("2 greyrock place", updatedOrder.getCustomerAddress());
		assertEquals(Long.valueOf(3l), updatedOrder.getOrderDetail().getQuantity());
	}

	@Test
	public void testDeleteOrderService() {

		Order savedOrder = ordersService.createOrder(new NewOrderRequest("Fred", "3 broad st", "TV", 1l));
		Order foundOrder = ordersService.getOrderById(savedOrder.getId());

		assertNotNull(foundOrder);

		assertEquals(savedOrder.getId(), foundOrder.getId());

		Order updatedOrder = ordersService
				.updateOrder(new UpdateOrderRequest(savedOrder.getId(), "steve", "1 ibm drive", "Iphone", 3l));
		assertNotNull(updatedOrder);

		ordersService.deleteOrder(savedOrder.getId());
		
	}

	public Callable<Boolean> createCallable(Order order, int tNumber, CyclicBarrier gate)
			throws OptimisticLockingFailureException {
		return () -> {
			UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest(order.getId(), "Alex",
					"2 canal st" + "[" + tNumber + "]", "Iphone", 3l);
			gate.await();
			Order updatedOrder = ordersService.updateOrder(updateOrderRequest);
			return true;
		};
	}

	@Test
	public void orderServiceShouldThrowOptimisticLockException() throws Throwable {

		Assertions.assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
			final int threads = 5;
			final CyclicBarrier gate = new CyclicBarrier(threads);
			ExecutorService executor = Executors.newFixedThreadPool(threads);
			

			Order savedOrder = ordersService.createOrder(new NewOrderRequest("Sandy", "3 clairemont blvd", "TV", 1l));

			List<Callable<Boolean>> tasks = new ArrayList<>();
			for (int i = 0; i < threads; i++) {
				tasks.add(createCallable(savedOrder, i, gate));
			}

			List<Future<Boolean>> result = executor.invokeAll(tasks);

			for (Future<Boolean> r : result) {
				try {
					Boolean temp = r.get();
					System.out.println("returned " + temp);
				} catch (ExecutionException e) {
					throw e.getCause();
				}
			}
		});
	}
}
