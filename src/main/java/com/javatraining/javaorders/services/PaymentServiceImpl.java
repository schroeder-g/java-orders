package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Payment;
import com.javatraining.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService
{
    @Autowired
    PaymentRepository payrepos;

    public Payment Save(Payment payment) {
        return payrepos.save(payment);
    }
}
