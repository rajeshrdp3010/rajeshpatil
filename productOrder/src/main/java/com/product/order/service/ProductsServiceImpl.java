package com.product.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.product.order.entity.Product;
import com.product.order.exception.ProductNotFoundException;
import com.product.order.repository.ProductsRepository;

@Service("ProductService")
public class ProductsServiceImpl implements ProductsService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);
	
	@Autowired
    private ProductsRepository productsRepository;
	
	@Override
	@Transactional
	public List<Product> getAllProducts() {
		return productsRepository.findAll();
	}
	
	@Override
	@Transactional
	public Product getProductById(Long productId) {
		logger.info("Exceuting getProductById -  productId:{}", productId);
		return productsRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found - " + productId));
	}
	
	@Override
	@Transactional
	public Product getProductByName(String productName) {
		logger.info("Exceuting getProductByName -  productName:{}", productName);
		return productsRepository.findByName(productName).stream().findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found - " + productName));
	}
	
	@Override
	@Retryable(maxAttempts=2, include = {ObjectOptimisticLockingFailureException.class, JpaSystemException.class}, backoff=@Backoff(delay = 1000))
	@Transactional
	public Product saveProduct(Product product) {
		logger.info("Exceuting saveProduct -  Product Info:{}", product.toString());
		return productsRepository.save(product);
	}
	
	@Override
	@Retryable(maxAttempts=2, include = {ObjectOptimisticLockingFailureException.class, JpaSystemException.class}, backoff=@Backoff(delay = 1000))
	@Transactional
	public void deleteProduct(Long productId) {
		logger.info("Exceuting deleteProduct -  Product Id:{}", productId);
		productsRepository.deleteById(productId);
		logger.info("Product deleted successfully:" + productId);
	}
	
	@Override
	@Recover
	@Transactional
	public void recover(ObjectOptimisticLockingFailureException e) {
		logger.info("Exceuting recover..."+ e.getMessage());
	}
}
