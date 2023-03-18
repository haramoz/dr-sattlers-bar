package com.dr.sattlers.bar.employee.waiter.service.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    private String tableId; //table_id
    private String status; //status

    public Table() {
    }

    public Table(String tableId, String status) {
        this.tableId = tableId;
        this.status = status;
    }
}
