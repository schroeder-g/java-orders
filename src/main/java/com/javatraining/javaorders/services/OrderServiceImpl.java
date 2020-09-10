package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Order;
import com.javatraining.javaorders.models.Payment;
import com.javatraining.javaorders.repositories.OrdersRepository;
import com.javatraining.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService
{
    @Autowired
    OrdersRepository orderrepos;

    @Autowired
    PaymentRepository payrepos;

    @Override
    public Order findOrderById(Long orderId) throws
            EntityNotFoundException
    {
        return orderrepos.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order id " + orderId + " not found!"));
    }

    @Transactional
    @Override
    public void delete(Long orderId)
    {
        if (orderrepos.findById(orderId).isPresent())
        {
            orderrepos.deleteById(orderId);
        } else
        {
            throw new EntityNotFoundException("Order num " + orderId + " Not found!");
        }
    }

    @Transactional
    @Override
    public Order save(Order order) {

        Order newOrder = new Order();

        if (order.getOrdnum() != 0)
        {
            findOrderById(order.getOrdnum());
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        newOrder.getPayments().clear();
        for(Payment p : order.getPayments())
        {
            Payment newPayment = payrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found!"));

            newOrder.getPayments().add(newPayment);
        }

        return orderrepos.save(order);
    }
}
