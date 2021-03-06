package com.product.order.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import com.product.order.entity.Product;

public interface ProductsRepository extends CrudRepository<Product, Long> {
	@Lock(value = LockModeType.OPTIMISTIC)
	List<Product> findByName(String name);
	
	@Lock(value = LockModeType.OPTIMISTIC)
	List<Product> findAll();
	
	@Lock(value = LockModeType.OPTIMISTIC)
	Optional<Product> findById(Long id);
}
