package com.javatraining.javaorders.repositories;

import com.javatraining.javaorders.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import views.OrderCounts;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByCustnameContainingIgnoringCase(String likename);

                                                //The count column must match fields stored in view
    @Query(value = "SELECT c.custname as name, count(ordnum) as countords " +
            "FROM customers c LEFT JOIN orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY countords desc", nativeQuery = true)
    List<OrderCounts> findOrderCounts();
}
