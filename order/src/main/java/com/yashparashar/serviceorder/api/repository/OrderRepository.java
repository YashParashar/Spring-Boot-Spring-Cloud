package com.yashparashar.serviceorder.api.repository;

import com.yashparashar.serviceorder.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order,Integer>{

}
