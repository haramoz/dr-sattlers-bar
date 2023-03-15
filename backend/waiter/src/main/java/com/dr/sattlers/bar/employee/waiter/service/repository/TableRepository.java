package com.dr.sattlers.bar.employee.waiter.service.repository;

import com.dr.sattlers.bar.employee.waiter.service.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Integer> {

    List<Table> findByStatus(String status);
}
