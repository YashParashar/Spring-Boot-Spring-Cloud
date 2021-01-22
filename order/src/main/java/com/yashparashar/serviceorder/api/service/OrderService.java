package com.yashparashar.serviceorder.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yashparashar.serviceorder.api.common.Payment;
import com.yashparashar.serviceorder.api.common.TransactionRequest;
import com.yashparashar.serviceorder.api.common.TransactionResponse;
import com.yashparashar.serviceorder.api.entity.Order;
import com.yashparashar.serviceorder.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repository;
    @Autowired
    private RestTemplate template;


    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        String response="";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        logger.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(request));
        Payment paymentResponse =  template.postForObject("http://PAYMENT-SERVICE/payment/savePayment",payment,Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")?"payment success":"failed";
        logger.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
        repository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
