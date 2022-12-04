package com.dr.sattlers.bar.employee.waiter.service.rest;

import lombok.Data;

@Data
public class Welcome {

    private final long tableId;
    private final String content;

    public Welcome(long tableId, String content) {
        this.tableId = tableId;
        this.content = content;
    }
}