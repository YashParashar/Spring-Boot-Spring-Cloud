package com.yashparashar.servicepayment.api.controller;

import com.yashparashar.servicepayment.api.entity.Payment;
import com.yashparashar.servicepayment.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;
    @PostMapping("/savePayment")
    public Payment savePayment(@RequestBody Payment payment){
        return service.savePayment(payment);
    }
}
