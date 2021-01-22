package com.yashparashar.serviceorder.api.service;

import com.yashparashar.serviceorder.api.common.Payment;
import com.yashparashar.serviceorder.api.common.TransactionRequest;
import com.yashparashar.serviceorder.api.common.TransactionResponse;
import com.yashparashar.serviceorder.api.entity.Order;
import com.yashparashar.serviceorder.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response="";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
      Payment paymentResponse =  template.postForObject("http://PAYMENT-SERVICE/payment/savePayment",payment,Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")?"payment success":"failed";
      repository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
