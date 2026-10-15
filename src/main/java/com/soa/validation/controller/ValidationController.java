package com.soa.validation.controller;

import com.soa.validation.dto.OrderRequest;
import com.soa.validation.exception.OrderValidationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ValidationController {

    private final Map<String, OrderRequest> orderMap = new HashMap<>();

    @PostMapping("/orders")
    public ResponseEntity<Map<String, OrderRequest>> createOrder(@RequestBody OrderRequest orderRequest) {
        if (orderRequest == null
                || orderRequest.customerName() == null || orderRequest.customerName().trim().isEmpty()
                || orderRequest.productName() == null || orderRequest.productName().trim().isEmpty()
                || orderRequest.quantity() == null || orderRequest.quantity() <= 0
                || orderRequest.price() == null || orderRequest.price() <= 0) {

            if (orderRequest == null) {
                throw new OrderValidationException("Order request is required");
            }
            if (orderRequest.customerName() == null || orderRequest.customerName().trim().isEmpty()) {
                throw new OrderValidationException("Customer name is required");
            }
            if (orderRequest.productName() == null || orderRequest.productName().trim().isEmpty()) {
                throw new OrderValidationException("Product name is required");
            }
            if (orderRequest.quantity() == null) {
                throw new OrderValidationException("Quantity is required");
            }
            if (orderRequest.quantity() <= 0) {
                throw new OrderValidationException("Quantity must be greater than zero");
            }
            if (orderRequest.price() == null) {
                throw new OrderValidationException("Price is required");
            }
            if (orderRequest.price() <= 0) {
                throw new OrderValidationException("Price must be greater than zero");
            }
        }

        orderMap.put(orderRequest.customerName(), orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMap);
    }
}