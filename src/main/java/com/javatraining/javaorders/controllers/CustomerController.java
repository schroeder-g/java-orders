package com.javatraining.javaorders.controllers;


import com.javatraining.javaorders.models.Customer;
import com.javatraining.javaorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import views.OrderCounts;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService custServ;

//    http://localhost:20/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> getAllCustomerOrders()
    {
        List<Customer> list = custServ.findAllCustomerOrders();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
//    http://localhost:2020/customers/customer/77
    @GetMapping(value = "/customer/{custid}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable Long custid)
    {
        Customer c = custServ.findCustomerById(custid);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

//    http://localhost:2020/customers/namelike/mes
    @GetMapping(value = "/namelike/{snippet}", produces = "application/json")
    public ResponseEntity<?> getCustomersByLikeName(@PathVariable String snippet)
    {
        List<Customer> rtnList = custServ.findByNameLike(snippet);

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // http://customers/orders/count
    // Using a custom query, return a list of all customers with the number of orders they have placed.
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getOrderCounts()
    {
        List<OrderCounts> rtnList = custServ.getOrderCounts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

   /* POST /customers/customer - Adds a new customer including any new orders */
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = custServ.save(newCustomer);

        // Response Headers -> Location Header = url to the new order
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    /* PUT /customers/customer/{custcode} - completely replaces the customer record including associated orders with the provided data */
    @PutMapping(value = "/customer/{customerid}", consumes = "application/json",
                                                produces = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updatingCustomer,
                                                @PathVariable Long customerid)
    {
        updatingCustomer.setCustcode(customerid);

        updatingCustomer = custServ.save(updatingCustomer);

        return new ResponseEntity<>(updatingCustomer ,HttpStatus.OK);
    }

   /* PATCH /customers/customer/{custcode} - updates customers with the new data. Only the new data is to be sent from the frontend client. */
   @PatchMapping(value = "/customer/{customerid}", consumes = "application/json",
                                                  produces = "application/json")
   public ResponseEntity<?> updatePartialCustomer(@RequestBody Customer patchingCustomer,
                                                  @PathVariable Long customerid)
   {
        patchingCustomer = custServ.update(patchingCustomer, customerid);
        return new ResponseEntity<>(patchingCustomer, HttpStatus.ACCEPTED);
   }

    /* DELETE /customers/customer/{custcode} - Deletes the given customer including any associated orders */
    @DeleteMapping(value = "/customer/{customerid}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerid)
    {
        custServ.delete(customerid);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}

