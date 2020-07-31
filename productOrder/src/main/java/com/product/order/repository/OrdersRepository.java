package com.product.order.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import com.product.order.entity.Order;

public interface OrdersRepository extends CrudRepository<Order, Long> {
	@Lock(value = LockModeType.OPTIMISTIC)
	List<Order> findAll();
	
	@Lock(value = LockModeType.OPTIMISTIC)
	Optional<Order> findById(Long id);
}
