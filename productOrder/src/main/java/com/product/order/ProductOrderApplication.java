package com.product.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;


@SpringBootApplication
@EnableRetry(proxyTargetClass=true)
public class ProductOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductOrderApplication.class, args);
	}

}
