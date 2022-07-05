package com.yashparashar.servicepayment.api.repository;

import com.yashparashar.servicepayment.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByOrderId(int orderId);
}
