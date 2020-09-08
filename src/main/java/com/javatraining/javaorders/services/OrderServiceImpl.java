package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Order;
import com.javatraining.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService
{
    @Autowired
    OrderRepository orderrepos;

    public Order save(Order order) {
        return orderrepos.save(order);
    }
}
