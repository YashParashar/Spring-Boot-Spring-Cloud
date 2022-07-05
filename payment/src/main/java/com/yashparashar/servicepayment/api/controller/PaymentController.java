package com.yashparashar.servicepayment.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yashparashar.servicepayment.api.entity.Payment;
import com.yashparashar.servicepayment.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;
    @PostMapping("/savePayment")
    public Payment savePayment(@RequestBody Payment payment) throws JsonProcessingException {
        return service.savePayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return service.findPaymentHistoryByOrderId(orderId);
    }
}
