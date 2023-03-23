package com.dr.sattlers.bar.employee.kitchen.service.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@javax.persistence.Table(name = "orders")
public class Order {
    @Id
    private String tableId; //table_id
    private String status; //status

    public Order() {
    }

    public Order(String tableId, String status) {
        this.tableId = tableId;
        this.status = status;
    }
}
