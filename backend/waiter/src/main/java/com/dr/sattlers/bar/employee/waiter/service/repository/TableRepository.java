package com.dr.sattlers.bar.employee.waiter.service.repository;

import com.dr.sattlers.bar.employee.waiter.service.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TableRepository extends JpaRepository<Table, String> {

    List<Table> findByStatus(String status);
}
