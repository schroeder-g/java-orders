package com.javatraining.javaorders.controllers;

import com.javatraining.javaorders.models.Order;
import com.javatraining.javaorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService ordServ;

    // GET order by id
    //  http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderid)
    {
        Order o = ordServ.findOrderById(orderid);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
//    POST /orders/order - adds a new order to an existing customer
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = ordServ.save(newOrder);

        // Response Headers -> Location Header = url to the new order
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

//  PUT /orders/order/{ordernum} - completely replaces the given order record
    @PutMapping(value = "/order/{ordernum}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> editOrderById(@Valid @RequestBody Order editOrder, @PathVariable Long ordernum)
    {
        editOrder.setOrdnum(ordernum);
        editOrder = ordServ.save(editOrder);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

//  DELETE /orders/order/{ordername} - deletes the given order
    @DeleteMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> deleteOrderByName(@PathVariable Long orderid)
    {
        ordServ.delete(orderid);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

}
