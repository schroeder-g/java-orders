package com.javatraining.javaorders.repositories;

import com.javatraining.javaorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
