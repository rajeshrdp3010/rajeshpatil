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

import com.product.order.entity.Order;
import com.product.order.request.NewOrderRequest;
import com.product.order.request.UpdateOrderRequest;
import com.product.order.service.OrdersService;

@RestController("OrdersController")
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;
    
    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> findAll() {
       List<Order> orderList = ordersService.getAllOrders();
       if (orderList == null || orderList.isEmpty()) {
           return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    
    @GetMapping(value = "/get/{orderId}")
    public ResponseEntity<Object> findOne(@PathVariable Long orderId) {
       Order order = ordersService.getOrderById(orderId);
       return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> save(@Valid @RequestBody NewOrderRequest orderRequest, UriComponentsBuilder ucBuilder) {
       Order order = ordersService.createOrder(orderRequest);
       HttpHeaders headers = new HttpHeaders();
       headers.setLocation(ucBuilder.path("/orders/get/{orderId}").buildAndExpand(order.getId()).toUri());
       return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@Valid @RequestBody UpdateOrderRequest orderRequest) {
       Order order = ordersService.updateOrder(orderRequest);
       return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId) {
       ordersService.deleteOrder(orderId);
       return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
