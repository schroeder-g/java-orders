package com.javatraining.javaorders.repositories;

import com.javatraining.javaorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
