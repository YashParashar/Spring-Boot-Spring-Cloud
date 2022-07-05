package com.yashparashar.servicepayment.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yashparashar.servicepayment.api.entity.Payment;
import com.yashparashar.servicepayment.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentService.class);
    @Autowired
    private PaymentRepository repository;

    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentprocessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        logger.info("Payment-Service Request : {}",new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }

    public String paymentprocessing(){
        //api of 3rd party payment gateway
        return new Random().nextBoolean()?"success":"false";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment = repository.findByOrderId(orderId);
        logger.info("paymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
