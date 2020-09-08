package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Agent;
import com.javatraining.javaorders.models.Customer;
import com.javatraining.javaorders.repositories.CustomerRepository;
import com.javatraining.javaorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository custrepos;

    public Customer save(Customer customer) {
        return custrepos.save(customer);
    }
}
