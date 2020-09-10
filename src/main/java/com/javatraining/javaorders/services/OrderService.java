package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Order;

public interface OrderService {
    Order findOrderById(Long orderId);

//    Order postNewOrder();

    void delete(Long orderId);

    Order save(Order order);
}
