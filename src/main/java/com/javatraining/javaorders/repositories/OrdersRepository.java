package com.javatraining.javaorders.repositories;

import com.javatraining.javaorders.models.Order;
import com.javatraining.javaorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Long> {
}
