package com.product.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.product.order.entity.OrderDetail;

public interface OrderDetailsRepository extends CrudRepository<OrderDetail, Long> { }
