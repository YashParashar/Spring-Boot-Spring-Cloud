package com.yashparashar.servicepayment.api.service;

import com.yashparashar.servicepayment.api.entity.Payment;
import com.yashparashar.servicepayment.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public Payment savePayment(Payment payment){
        payment.setPaymentStatus(paymentprocessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    public String paymentprocessing(){
        return new Random().nextBoolean()?"success":"false";
    }
}
