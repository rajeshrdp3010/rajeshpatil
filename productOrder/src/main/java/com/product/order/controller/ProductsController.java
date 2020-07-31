package com.product.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.product.order.entity.Product;
import com.product.order.service.ProductsService;

@RestController("ProductsController")
@RequestMapping("/products")
@CrossOrigin
public class ProductsController {
    
    @Autowired
    private ProductsService productsService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<Object>  findAll() {
       List<Product> productList = productsService.getAllProducts();
       if (productList == null || productList.isEmpty()) {
           return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    
    @GetMapping(value = "get/{productId}")
    public ResponseEntity<Object> findOne(@PathVariable Long productId) {
       Product product = productsService.getProductById(productId);
       return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    @PostMapping(value = "add")
    public ResponseEntity<Void> save(@Valid @RequestBody Product product, UriComponentsBuilder ucBuilder) {
       Product savedProduct = productsService.saveProduct(product);
       HttpHeaders headers = new HttpHeaders();
       headers.setLocation(ucBuilder.path("/products/get/{productId}").buildAndExpand(savedProduct.getId()).toUri());
       return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@Valid @RequestBody Product product) {
       Product savedProduct = productsService.saveProduct(product);
       return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
    	productsService.deleteProduct(productId);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
