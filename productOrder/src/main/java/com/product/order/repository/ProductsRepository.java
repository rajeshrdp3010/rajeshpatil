package com.product.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.product.order.entity.Product;

public interface ProductsRepository extends CrudRepository<Product, Long> { }
