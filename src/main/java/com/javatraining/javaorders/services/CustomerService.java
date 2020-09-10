package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Agent;
import com.javatraining.javaorders.models.Customer;
import views.OrderCounts;

import java.util.List;

public interface CustomerService {

//    List<Customer> findAllCustomers();

    /**
     * Returns a list of all the customers and their orders.
     *
     * @return List of Restaurant. If no Restaurant, empty list.
     */
    List<Customer> findAllCustomerOrders();

    /**
     * Returns the restaurant with the given primary key.
     *
     * @param id The primary key (long) of the restaurant you seek.
     * @return The given Restaurant or throws an exception if not found.
     */

    Customer findCustomerById (Long custId);

    /**
     * A list of all customers whose name contains the given substring.
     *
     * @param thename The substring (String) of name you wish to search for.
     * @return A list of all custs  whose name contains the given substring. If no matching customer, empty list.
     */
    List<Customer> findByNameLike(String name);

    List<OrderCounts> getOrderCounts();

    void delete(Long custId);

    Customer save(Customer customer);

    Customer update(Customer customer, Long custid);

}
