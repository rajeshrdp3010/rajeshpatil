package com.product.order.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.product.order.entity.Product;

public interface ProductsService {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Long productId);
	
	public Product getProductByName(String productName);
	
	public Product saveProduct(Product product);
	
	public void deleteProduct(Long productId);
	
	public void recover(ObjectOptimisticLockingFailureException e);
}
