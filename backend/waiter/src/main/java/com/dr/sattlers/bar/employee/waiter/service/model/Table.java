package com.dr.sattlers.bar.employee.waiter.service.model;
import lombok.Data;

@Data
public class Table {
    private String tableId; //table_id
    private String status; //status

    public Table(String tableId, String status) {
        this.tableId = tableId;
        this.status = status;
    }
}
