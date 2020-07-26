package com.product.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.order.config.H2JpaConfig;
import com.product.order.entity.Product;
import com.product.order.repository.ProductsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductOrderApplication.class,H2JpaConfig.class})
@ActiveProfiles("test")
class ProductOrderApplicationTests {

	@Autowired
	private ProductsRepository productRepository;
	
	@Test
	public void testProductRepository() {
		Product savedProduct = productRepository.save(new Product("tableFan",30l));
		Product foundProduct = productRepository.findById(savedProduct.getId()).get();
		
		assertNotNull(foundProduct);
		
		assertEquals(savedProduct.getId(),foundProduct.getId());
	}

}
