package com.dr.sattlers.bar.employee.kitchen.service.repository;
import com.dr.sattlers.bar.employee.kitchen.service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}