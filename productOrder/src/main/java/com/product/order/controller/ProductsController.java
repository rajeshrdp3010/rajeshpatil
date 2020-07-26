package com.product.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.product.order.entity.Product;
import com.product.order.repository.ProductsRepository;

@RestController("JpaProductsController")
@RequestMapping("/jpa-products")
public class ProductsController {
    
    @Autowired
    private ProductsRepository repo;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable Long id) {
       return repo.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product save(@RequestBody Product product) {
       return repo.save(product);
    }
}
